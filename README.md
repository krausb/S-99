# S-99 #

Welcome to S-99 - The 99 Scala problems puzzle!

This project is dedicated to solve a puzzle which originates from the [99 Prolog problems](http://www.ic.unicamp.br/~meidanis/courses/mc336/2009s2/prolog/problemas/). The description of the S-99 puzzle can be found [here](http://aperiodic.net/phil/scala/s-99/).

## Project Structure ##

The solution for each task is located in its own ScalaTest source file structured by the following pattern:
P#Nr#.scala. The proof of the solution will be made through the execution of the unit test.

The problem itself is described in the comment block of the ScalaTest case. The solution is then modeled as the 
corresponding test. 

## Proof of correctness ##

To execute the proof of correctness just run the unit tests:
```
&> sbt clean compile test
```

## Contribution policy ##

Contributions via GitHub pull requests are gladly accepted from their original author. Along with
any pull requests, please state that the contribution is your original work and that you license
the work to the project under the project's open source license. Whether or not you state this
explicitly, by submitting any copyrighted material via pull request, email, or other means you
agree to license the material under the project's open source license and warrant that you have the
legal authority to do so.

## License ##

This code is open source software licensed under the
[GPL-3.0](http://www.gnu.org/licenses/gpl-3.0.en.html) license.
