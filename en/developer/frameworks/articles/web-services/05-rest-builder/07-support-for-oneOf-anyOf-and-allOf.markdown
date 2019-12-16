---
header-id: rest-builder-support-for-oneof-anyof-and-allof
---

# Support for oneOf, anyOf and allOf

[TOC levels=1-4]

OpenAPI 3.0 added several ways of using inheritance and composition to create complex schemas. Specifically, it added support for [_allOf_, _anyOf,_ and _oneOf_](https://swagger.io/docs/specification/data-models/oneof-anyof-allof-not/), with these semantics:

* allOf – the value validates against all the subschemas 
* anyOf – the value validates against any of the subschemas
* oneOf – the value validates against exactly one of the subschemas

Let's see the syntax and generated code of every option:

## allOf

With _allOf_ we can use the power of composition to create an entity that combines several others. It's the most potent way of reusing code without losing expressiveness and granularity: we can define small entities that can be reused by composing several to create a larger entity.

To be able to use _allOf_ we have to follow this syntax:

```yaml
EntityA:
    properties:
        nameA:
            type: string
EntityB:
    properties:
        nameB:
            type: string
EntityC:
    allOf:
        - $ref: '#/components/schemas/EntityA'
        - $ref: '#/components/schemas/EntityB'
```

This OpenAPI syntax will generate the following Java code inside the _EntityC_ class:

```java
@Schema
@Valid
public EntityA getEntityA() {
    return entityA;
}

@Schema
@Valid
public EntityB getEntityB() {
    return entityB;
}
```

## oneOf

_OneOf_ is the simplest of the generics properties, it allows us to define a property that can have different types. Due to Java not supporting Union Types, this just means that we use an Object to model the property:

```yaml
EntityA:
    properties:
        nameA:
            type: string
EntityB:
    properties:
        nameB:
            anyOf:
                - $ref: "#/components/schemas/EntityA"
                - type: object
                  properties:
                      name:
                          type: string
```

This syntax will generate the following Java code:

```java
@Schema
@Valid
public Object getNameB() {
    return nameB;
}
```

## anyOf

And the last one of the generic keywords, _anyOf_ leverages _JsonSubTypes_ to allow us to extend entities with properties using inheritance. We can define parent relationships (in our example, _EntityC_) that has two children with the properties of the parent and their own properties. The YAML to use inheritance in our OpenAPI profile is the following:

```yaml
EntityC:
    oneOf:
        - properties:
            nameA:
                type: string
        - properties:
            nameB:
                type: string
    properties:
        nameC:
            type: string
```

This generates a parent class with two children:

```java
@JsonSubTypes(
    {
        @JsonSubTypes.Type(name = "nameA", value = NameA.class),
        @JsonSubTypes.Type(name = "nameB", value = NameB.class)
    }
)
@JsonTypeInfo(
    include = JsonTypeInfo.As.PROPERTY, property = "childType",
    use = JsonTypeInfo.Id.NAME
)
@Generated("")
@GraphQLName("EntityC")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "EntityC")
public class EntityC {

    @Schema
    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }
```

And two children classes, like this one:

```java
@JsonTypeInfo(
    defaultImpl = NameA.class, include = JsonTypeInfo.As.PROPERTY,
    property = "childType", use = JsonTypeInfo.Id.NAME
)
@Generated("")
@GraphQLName("NameA")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "NameA")
public class NameA extends EntityC {

    @Schema
    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }
```