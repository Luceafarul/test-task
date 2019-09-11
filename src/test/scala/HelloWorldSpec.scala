import org.specs2.Specification
import org.specs2.matcher.MatchResult

class HelloWorldSpec extends Specification { def is = s2"""

 This is a specification to check the 'Hello world' string

 The 'Hello world' string should
   contain 11 characters                                         $e1
   start with 'Hello'                                            $e2
   end with 'world'                                              $e3
                                                                 """

  def e1: MatchResult[String] = "Hello world" must have size(11)
  def e2: MatchResult[String] = "Hello world" must startWith("Hello")
  def e3: MatchResult[String] = "Hello world" must endWith("world")
}