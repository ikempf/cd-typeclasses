package ce

import cats.Functor
import play.api.libs.json.{JsNull, JsNumber, Json, Writes}

/**
  * Code reuse techniques
  */
object Dry extends App {

  case class Person(name: String, age: Int)
  case class JobOffer(code: Int, description: String)

  /** Approach 1 - no abstraction */
  object Naive {
    def cacheEntry(person: Person) =
      (s"person::${person.name}", person.toString)

    def cacheEntry(jobOffer: JobOffer) =
      (s"job::${jobOffer.code}", jobOffer.toString)

    // How to write this polymorphic function ?
    def putInCache(as: List[_]) = {
      //val entries = as.map(cacheEntry)
      //redis.put(entries)
      ???
    }
  }

  /** Approach 2 - OO abstractions */
  object OoSubtyping {
    trait Cacheable {
      val key: String
      val body: String
    }

    case object Pouet extends Cacheable {
      override val key: String  = ???
      override val body: String = ???
    }

    def cacheEntriesOo[A <: Cacheable](as: List[A]) =
      as.map(a => (a.key, a.body))

    cacheEntriesOo(List.empty[Pouet.type])
  }

  // Concerns are coupled in the OO approach. Between layers and between domain logic
  // How can we keep our code dry in FP (and loosely coupled ?)

  /** Approach 3 - Manual typeclasses */
  object FakeTypeclass {
    trait Caching[A] {
      def key(a: A): String
      def body(a: A): String
    }

    object Caching {
      val personCaching = new Caching[Person] {
        override def key(a: Person)  = s"persons${a.name}"
        override def body(a: Person) = a.toString
      }
    }

    def cacheEntriesTC[A](as: List[A])(caching: Caching[A]) =
      as.map(a => (caching.key(a), caching.body(a)))

    cacheEntriesTC(List.empty[Person])(Caching.personCaching)
  }

  /** Approach 4 - Typeclass abstraction */
  object Typeclass {
    trait Caching[A] {
      def key(a: A): String
      def body(a: A): String
    }

    object Caching {
      implicit val personCaching = new Caching[Person] {
        override def key(a: Person)  = s"persons${a.name}"
        override def body(a: Person) = a.toString
      }
    }

    def cacheEntriesTC[A](as: List[A])(implicit ev: Caching[A]) =
      as.map(a => (ev.key(a), ev.body(a)))

    // Typeclass syntax style
    def sum[A: Numeric](l: List[A]) =
      l.sum

    cacheEntriesTC(List.empty[Person])
  }

  /** Implicit chaining */
  object ImplicitChaining {
    val writesInt = Writes[Int](JsNumber(_))

    // Duplicating Int write logic ?
    val writesOptionInt =
      Writes[Option[Int]](opt =>
        opt
          .map(JsNumber(_))
          .getOrElse(JsNull)
      )

    def writesOption[A: Writes] =
      Writes[Option[A]](opt =>
          opt
            .map(Json.toJson(_))
            .getOrElse(JsNull)
      )
  }
}
