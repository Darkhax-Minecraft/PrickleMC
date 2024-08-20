<!-- name-start -->
# PrickleMC [![CurseForge Project](https://img.shields.io/curseforge/dt/1023259?logo=curseforge&label=CurseForge&style=flat-square&labelColor=2D2D2D&color=555555)](https://www.curseforge.com/minecraft/mc-mods/prickle) [![Modrinth Project](https://img.shields.io/modrinth/dt/aaRl8GiW?logo=modrinth&label=Modrinth&style=flat-square&labelColor=2D2D2D&color=555555)](https://modrinth.com/mod/prickle) [![Maven Project](https://img.shields.io/maven-metadata/v?style=flat-square&logoColor=D31A38&labelColor=2D2D2D&color=555555&label=Latest&logo=gradle&metadataUrl=https%3A%2F%2Fmaven.blamejared.com%2Fnet%2Fdarkhax%2Fpricklemc%2Fprickle-common-1.21%2Fmaven-metadata.xml)](https://maven.blamejared.com/net/darkhax/pricklemc)
<!-- name-end -->
Prickle is a lightweight configuration format based on JSON. Prickle aims
to be simple, accessible to everyday users, and be very easy to parse and
generate with code. This library implements the Prickle format as a
library that is compatible with Minecraft and various popular mod loaders.

## Config Format

### Properties
In Prickle the value of all properties are wrapped in JSON objects. This 
allows metadata and other properties to be associated with that property.
The value of the property is held by the `value` property. The value must
be a valid JSON Element.

**Standard JSON**
```json
{
  "database_host": "192.168.1.222"
}
```

**Prickle Property**
```json
{
  "database_host": {
    "value": "192.168.1.222"
  }
}
```

### Comments
The `//` key is used to define property comments. Comments should only be
used to provide additional context to the reader and should never 
influence how the file is parsed or used by code. Comments can be a JSON 
string or an array of JSON strings for multiline comments.

**Single Line Comment**
```json
{
  "database_host": {
    "//": "The IP address of the database to connect to.",
    "value": "192.168.1.0"
  }
}
```

**Multi-line Comment**
```json
{
  "database_host": {
    "//": [
      "The IP address of the database to connect to.",
      "The port can be suffixed using a colon.      "
    ],
    "value": "192.168.1.0"
  }
}
```

### Decorators
Decorators are named comments that convey a specific attribute of the 
property to the reader. Decorators are declared using the comment key
followed by the name of the attribute. For example the `//default`
decorator might be used to denote the default value of the property.
Like comments, decorators should never influence how the file is parsed
or used by code.

Decorators and their meanings are entirely up to the author of the 
config schema however the following decorators are commonly used
and are made available by this implementation of Prickle.
- `//default` - The default value for the property.
- `//reference` - Points the reader to another resource that can be used to learn more about the property, like a wiki/docs page.
- `//range` - The value must be within the given range.
- `//regex` - The value must match the regex pattern.
- `//empty-allowed` - The value can be empty.

## FaQ

### Why is this called Prickle?
Prickle is the collective noun for a group of hedgehogs. Hedgehogs are one
of my favourite animals and I think they are a fitting metaphor for config
files. For example, they both may seem intimidating at first but can be
nice once you get to know and understand them.

### Why not use an existing format?
Before working on Prickle I spent several months testing existing formats.
I found several that I liked but was disappointed with a lot of the Java 
implementations. Every library that I tried had serious long-standing 
bugs, was not being maintained, and lacked features that were important to
me. After weighing my options I realized JSON could easily meet all of my
criteria and the tools/libraries to work with JSON are widely available, 
and extensively tested.

### Can I JiJ Prickle?
This library was not designed to be used with jar-in-jar tools. This use
case is not recommended or supported.

<!-- maven-start -->
## Maven Dependency

If you are using [Gradle](https://gradle.org) to manage your dependencies, add the following into your `build.gradle` file. Make sure to replace the version with the correct one. All versions can be viewed [here](https://maven.blamejared.com/net/darkhax/pricklemc).

```gradle
repositories {
    maven { 
        url 'https://maven.blamejared.com'
    }
}

dependencies {
    // NeoForge
    implementation group: 'net.darkhax.pricklemc' name: 'prickle-neoforge-1.21' version: '21.0.0'

    // Forge
    implementation group: 'net.darkhax.pricklemc' name: 'prickle-forge-1.21' version: '21.0.0'

    // Fabric & Quilt
    modImplementation group: 'net.darkhax.pricklemc' name: 'prickle-fabric-1.21' version: '21.0.0'

    // Common / MultiLoader / Vanilla
    compileOnly group: 'net.darkhax.pricklemc' name: 'prickle-common-1.21' version: '21.0.0'
}
```
<!-- maven-end -->

<!-- sponsor-start -->
## Sponsors

[![](https://assets.blamejared.com/nodecraft/darkhax.jpg)](https://nodecraft.com/r/darkhax)    
PrickleMC is sponsored by Nodecraft. Use code **[DARKHAX](https://nodecraft.com/r/darkhax)** for 30% of your first month of service!
<!-- sponsor-end -->