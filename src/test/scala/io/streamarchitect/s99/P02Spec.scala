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
  * P02: Find the last but one element of a list.
  *
  * Example:
  * {{{
  * scala> penultimate(List(1, 1, 2, 3, 5, 8))
  * res0: Int = 5
  * }}}
  */
class P02Spec extends WordSpecLike with MustMatchers {

  "P02" should {

    "return the penultimate element of a List[A] using list methods" in {

      P02Spec.probe01.init.last mustBe 4
      P02Spec.probe02.init.last mustBe 4
      P02Spec.probe03.init.last mustBe 1

    }

    "return the penultimate element of a List[A] using recursion" in {

      penultimateRecursion(P02Spec.probe01) mustBe 4
      penultimateRecursion(P02Spec.probe02) mustBe 4
      penultimateRecursion(P02Spec.probe03) mustBe 1

    }

  }

  private def penultimateRecursion[A](l: List[A]): A = l match {
    case penultimate :: _ :: Nil  => penultimate
    case _ :: tail    => penultimateRecursion(tail)
    case _            => throw new Exception("No such element")
  }

}

object P02Spec {

  val probe01 = List(1, 2, 3, 3, 2, 4, 8)
  val probe02 = List(4, 5, 6, 9, 3, 4, 1)
  val probe03 = List(3, 7, 4, 6, 5, 1, 9)

}
