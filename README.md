# Scala Case Class Copier

A library to copy values from one Type of case class to another Type. Useful when you have need copy data to different Types, eg you may have an entity for REST requests that is very similar to you DB entity.

## Example

    case class SourceClass(
      id: Long,
      name: String,
      age: Int,
      timestamp: Long // ignored
    )

    case class DestClass(
      id: Long,
      name: String,
      age: Int
    )
  
    // Will create the following for you
    val Copier = new CaseClassCopier[SourceClass, DestClass] {
      def copy(source: SourceClass): DestClass =
        DestClass(
          id = source.id,
          name = source.name,
          age = sourec.age
        )
    }

## Usage

TBC

## Behaviours

* Fields must have the same name and type
* Fields in the source that do not exist on the destination are ignored


## TODO

* Implicit Support
* Error checking and error messages
* Support for Option, eg, Int => Option[Int]
* Support for user defined defaults
* Support for missing source fields
* Support for custom logic, eg feilds value based of other fields


# License

Copyright (c) 2015 Kalman Bekesi

Published under the [Apache License 2.0](http://en.wikipedia.org/wiki/Apache_license).
