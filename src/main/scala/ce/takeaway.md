#Decouple behaviours
Avoid ugly subtyping: hashCode, equals, toString
Classify types from the "outside" with typclasses like Order[A], Show[A], Eq[A]
Avoid intrusive libs like Jackson, Hibernate, DynamoDb, Slick...

#Extend existing code
Add behaviours to existing types (A => Behaviour[A])

#And more
- Abstract over type constructors (Traverse[F[_]], BiFunctor[F[_, _]])
- Use types and not objects (Object.equals, Comparable[A] are broken...)
- Isolate behaviours for type A
- Stack behaviours horizontally and not vertically

#Some naming conventions
Interface names describe the capacity of the type implementing types
Typeclass names describe the operations of the typeclass
Example: Reads vs Serializable

Typeclasses are sometimes called "witness" or "evidence"
