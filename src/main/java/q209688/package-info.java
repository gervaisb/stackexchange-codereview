/**
 * We have been given a list of strings which are blacklisted. The goal is to
 * identify if a given text contains any of these blacklisted strings.
 *
 * The restriction here is that the blacklisted string needs to match on the word
 * boundary e.g. consider a blacklist string "abc" and text "abc pqr", the text
 * in this case is unsafe (i.e. it contains a blacklisted string).
 * On the other hand if the text is "abcoqr", then the text is safe since the
 * string "abc" is not on the word boundary.
 *
 */
package q209688;
