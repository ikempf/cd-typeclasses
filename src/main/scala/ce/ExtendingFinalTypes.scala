package ce

object ExtendingFinalTypes extends App {

  trait HumanReadableString {
    def hr: String
  }

  case class Person(name: String) extends HumanReadableString {
    override def hr: String = s"Person($name)"
  }

  // Extending String ??
  object AdapterStyle {
    case class StringAdapter(string: String) extends HumanReadableString {
      override def hr: String = ???
    }
  }

  // Need to switch between wrapper and non-wrapped typed :/

  object TypeclassStyle {
    trait HumanFormat[A] {
      def hr(a: A): String
    }

    implicit val personFormat = new HumanFormat[Person] {
      override def hr(a: Person): String = a.name
    }
  }

}
