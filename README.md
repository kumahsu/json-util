# Json Util

---

| Version | Author | Date       |
|:-------:|:------:|:----------:|
| 0.1     | Kuma   | 2016/09/10 |

---

### Usage

#### Json Builder

Sample:

```java
JsonBuilder builder = new JsonBuilder();
builder.appendString("arg1", "arg")
       .appendNumber("arg2", 123)
       .appendBoolean("arg3", true);
JsonObject jsonObj = builder.build();

String json = jsonObj.toJson();
String prettyJson = jsonObj.toPrettyJson();
```

#### Array Builder

Sample:

```java
ArrayBuilder builder = new ArrayBuilder();
builder.addString("element")
		.addNumber(1)
		.addBoolean(true)
		.addObject(new JsonBuilder().appendString("arg1", "arg").build());
JsonArray jsonArr = builder.build();

String json = jsonArr.toJson();
String prettyJson = jsonArr.toPrettyJson();
```

### Release Note