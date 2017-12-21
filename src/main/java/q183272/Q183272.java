package q183272;

import java.io.StringReader;

/*
 * I had an interview yesterday where I was asked to write a function that
 * would take in a list containing a date and the amount of money spent on that
 * date, and to output the total amount spent per month. The data was for an
 * energy company, so the amount spent corresponds to the amount of energy used
 * that day.
 *
 * Some conditions were:
 *   + The list will only contain 1 years worth of data
 *   + This list is in the order each usage/transaction occurred
 *   + But the list can start at any point and date within the year (i.e if it
 *     started on Feb 2nd 2017, it would only contain data up till Feb 1st 2018)
 *
 * They left it kind of open after that on how to proceed. I didn't manage to
 * get it done in the interview, although I think I had the right approach.
 *
 * The sample data looks like this:
 * > (("2017-02-13", 1200.00), ("2017-02-19", 50.00), ("2017-04-10", 100.45), etc..)
 *
 * My solution would output a table similar to the following:
 * >     Month-Year   |  Usage
 * > --------------------------------
 * > January-2017     |  2400.0
 * > February-2017    |  1250.0
 * > April-2017       |  100.45
 * > May-2017         |  225.0
 * > December-2017    |  460.9
 * > January-2018     |  1550.0
 *
 * My code is below. Please let me know if you think there is simpler way to do
 * this. I thought maybe instead of a Map, I could have another class called
 * `MonthlyUsage`, and make a list of that instead to hold the data, but I
 * wasn't too sure how to implement that with this approach.
 */
public class Q183272 {
   public static void main(String[] args) {
       ConsumptionsReader reader = new ConsumptionsReader(new StringReader(
            "((\"2017-02-13\", 1200.00), (\"2017-02-19\", 50.00), (\"2017-04-10\", 100.45))"));
       MonthlyConsumptionReport report = new MonthlyConsumptionReport(reader);
       report.writeTo(System.out);
   }

}
