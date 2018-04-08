/** Subtyping vs typeclasses */
case class Person(name: String, age: Int) extends Cacheable {
  override val key = s"person$name"
  override val body = toString
}
case class JobOffer(code: Int, description: String) extends Cacheable {
  override val key = s"job::$code"
  override val body = toString
}


trait Cacheable {
  val key: String
  val body: String
}

def cacheEntry(person: Person) =
  (s"person${person.name}", person.toString)

def cacheEntry(jobOffer: JobOffer) =
  (s"job::${jobOffer.code}", jobOffer.toString)

def cacheEntries[A <: Cacheable](as: List[A]) =
  as.map(a => (a.key, a.body))

// Typeclasses should describe the operations
// Subtyping describes the capacity of the type
// Example: Reads vs Serializable

// Decoupling code, hashcode, equals, toString, compareTo

/** Extending existing types */
// Ugly subtyping in Java: hashCode, equals, toString


/** Using existing typeclasses */
case class Surface(height: Int, width: Int)
case class Order(unitPrice: Double, quantity: Double)

def sum(l: List[Surface]): Surface =
  l.reduce((s1, s2) => Surface(s1.height + s2.height, s1.width + s2.width))

// Objectif
trait NumberLike[A] {
  def add(a: A, b: A): A
  def subtract(a: A, b: A): A
}

object NumberLike {
  implicit val surfaceNumberLike = new NumberLike[Surface] {
    override def add(a: Surface, b: Surface) =
      Surface(a.height + b.height, a.width + b.width)

    override def subtract(a: Surface, b: Surface) =
      Surface(a.height - b.height, a.width - b.width)
  }
}


// Add syntax to typeclass
implicit class NumberLikeOps[A](a: A)(implicit ev: NumberLike[A]) {
  def subtract(b: A): A =
    ev.subtract(a, b)

  def -(b: A): A =
    subtract(b)
}

Surface(1, 2) - Surface(1, 2)

// NumberLike should be called Numeric

