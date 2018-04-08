/**
  * Subtyping vs typeclasses
  */
case class Person(name: String, age: Int) extends Cacheable {
  override val key = s"person$name"
  override val body = toString
}
case class JobOffer(code: Int, description: String) extends Cacheable {
  override val key = s"job::$code"
  override val body = toString
}

/** Approach 1 - no abstraction */
def cacheEntry(person: Person) =
  (s"person${person.name}", person.toString)

def cacheEntry(jobOffer: JobOffer) =
  (s"job::${jobOffer.code}", jobOffer.toString)

def cacheEntries[A](as: List[A]) =
  ???

// How can we keep our code dry in OO ?

/** Approach 2 - OO abstraction */
trait Cacheable {
  val key: String
  val body: String
}
def cacheEntriesOO[A <: Cacheable](as: List[A]) =
  as.map(a => (a.key, a.body))

cacheEntriesOO(List.empty[Person])

// Concerns are couple in the OO approach. Between layers and between domain logic

// How can we keep our code dry in FP

/** Approach 3 - Typeclass abstraction */
trait Caching[A]{
  def key(a: A): String
  def body(a: A): String
}

object Caching {
  implicit val personCaching = new Caching[Person] {
    override def key(a: Person) = s"persons${a.name}"
    override def body(a: Person) = a.toString
  }
}

def cacheEntriesTC[A](as: List[A])(implicit ev: Caching[A]) =
  as.map(a => (ev.key(a), ev.body(a)))

cacheEntriesTC(List.empty[Person])

// Typeclasses should describe the operations
// Subtyping describes the capacity of the type
// Example: Reads vs Serializable

// Take away: Decouple code (slick, jackson etc. are intrusive)
// Extend existing types