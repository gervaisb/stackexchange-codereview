package cli;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Question {
    private final Element question;
    private final String id;
    public Question(String id) throws IOException {
        this.question = Jsoup.connect("http://codereview.stackexchange.com/q/"+id).get()
            .select("#question div.post-text").first();
        this.id = id;
    }

    public void writeTo(File folder) throws IOException {
        File dir = new File(folder, "q"+id);
        File mai = new File(dir, "Q"+id+".java");
        dir.mkdirs();
        FileWriter writer = new FileWriter(mai);
        writer.append(
        "package q"+id+";\n" +
        "\n"+
        "/*\n");

        for (Element child : question.children()) {
            if ( child.tagName().equals("p") ) {
                writer.append(new Comment(child).asIs());
            } else if ( child.tagName().equals("pre") &&
                        child.children().isEmpty() ) {
                writer.append(new Comment(child).asIs());
            } else if ( child.tagName().equals("pre") &&
                        child.child(0).tagName().equals("code") ) {
                if ( child.child(0).text().contains("class") ) {
                    addClass(dir, child.child(0));
                } else {
                    writer.append(new Comment(child).asIs());
                }
            }
        }
        writer.append(
        " */\n"+
        "public class Q"+id+" {\n" +
        "   public static void main(String[] args) {\n" +
        "       System.out.println(\n" +
        "           /* TODO, describe usage */);\n" +
        "       \n" +
        "       \n" +
        "   }\n"+
        "}\n").close();
    }

    private void addClass(File folder, Element code) throws IOException {
        int from = code.text().indexOf("class ")+"class ".length();
        int to = code.text().indexOf(" ", from+1);
        String className = code.text().substring(from, to).trim();
        new FileWriter(new File(folder, className+".java"))
                .append("package q"+id+";\n")
                .append("\n")
                .append(code.text())
                .append("\n")
                .close();
    }

    private class Comment {
        private final Element p;

        public Comment(Element p) {
            this.p = p;
        }

        public String asIs() {
            final StringBuilder comment = new StringBuilder(80);
            final StringBuilder buffer = new StringBuilder(80);
            final String[] words = p.text().split(" ");

            for (int i=0; i<words.length; i++) {
                if (buffer.length()+words[i].length()<80) {
                    buffer.append(words[i]).append(' ');
                } else {
                    comment.append(" * ").append(buffer).append(" \n");
                    buffer.delete(0, buffer.length());
                }
            }
            return comment.toString();
        }
    }
}
