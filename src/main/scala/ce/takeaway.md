###Decouple behaviours
Avoid ugly subtyping: hashCode, equals, toString
Classify types from the "outside" Order[A], Show[A], Eq[A]...

Avoid intrusive libs Jackson, Hibernate, DynamoDb, Slick...

###Extend existing code
Add behaviours to existings types.
A => Behaviour[A]

###Naming conventions
Interface names describe the capacity of the type implementing types
Typeclass names describe the operations of the typeclass
Example: Reads vs Serializable

Typeclasses are often named "witness" or "evidence"
