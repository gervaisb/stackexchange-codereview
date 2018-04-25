package q192870;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Optional;

class AnswersForUserAccount implements Answer {
    public static Answer get() {
        return new AnswersForUserAccount();
    }

    private AnswersForUserAccount(){/**/}

    @Override
    public Object answer(InvocationOnMock invocation) {
        Class type = invocation.getMethod().getReturnType();
        if ( String.class.isAssignableFrom(type) ) {
            return "";
        } else if ( Optional.class.isAssignableFrom(type) ) {
            return Optional.empty();
        } else {
            return null;
        }
    }
}
