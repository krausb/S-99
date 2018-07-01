/*
 * Copyright (C) 2018  Bastian Kraus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.streamarchitect.s99

import org.scalatest.{MustMatchers, WordSpecLike}

/**
  * P01: Find the last element of a list.
  *
  * Example:
  * {{{
  * scala> last(List(1, 1, 2, 3, 5, 8))
  * res0: Int = 8
  * }}}
  */
class P01Spec extends WordSpecLike with MustMatchers {

  "P01" should {

    "return the last element of a List[A] using list methods" in {

      P01Spec.probe01.last mustBe 8
      P01Spec.probe02.last mustBe 1
      P01Spec.probe03.last mustBe 9

    }

    "return the last element of a List[A] using recursion" in {

      lastByRecursion(P01Spec.probe01) mustBe 8
      lastByRecursion(P01Spec.probe02) mustBe 1
      lastByRecursion(P01Spec.probe03) mustBe 9

    }

  }

  private def lastByRecursion[A](l: List[A]): A = l match {
    case last :: Nil  => last
    case _ :: tail    => lastByRecursion(tail)
    case _            => throw new Exception("No such element")
  }

}

object P01Spec {

  val probe01 = List(1, 2, 3, 3, 2, 4, 8)
  val probe02 = List(4, 5, 6, 9, 3, 4, 1)
  val probe03 = List(3, 7, 4, 6, 5, 1, 9)

}
