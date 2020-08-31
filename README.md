# Not actively maintained

<h1 align="center">
    <img src="./artwork/Full%20Lockup/Full%20Color/Logo.png" alt="Logo" width="256">
</h1>

###### Artwork by @PublicByte, see [artwork/README.md](./artwork/README.md)

Juklear is a Java wrapper around the popular immediate mode GUI 
[Nuklear](https://github.com/Immediate-Mode-UI/Nuklear) providing both an immediate, and a 
classic node graph mode for easy use.
 
### About the project
Juklear is being developed for use in Java games without its base having external 
dependencies (except Nuklear of course). The Java wrapper is hand written and hides the
cumbersome aspects of C behind a convenient Java interface with automatic native resource
management.

### Backends
The library does not depend on a particular rendering engine, but provides a way to simply
plug in your own. However, an OpenGL backend for LWJGL3 will be provided in this repository
too. This includes exposing the API of Nuklear, allowing a simple implementation of
other rendering backends.

### Using the library
Currently, there are no builds available, this will change as soon as the library gets to a
more complete state. In the meantime you can build it yourself, see below for more info.
Examples will also be added once the library is more complete, currently the LWJGL3-OpenGL
backend tests can be used as examples.
 
#### Immediate mode
Juklear exposes the methods of Nuklear using JNI. Those methods can be used when desired,
they resemble the C interface of Nuklear nearly directly.

#### Node Graph mode
As typical for Java, Juklear also exposes a more object oriented approach, resembling GUI 
Frameworks such as Swing or JavaFX. Internally the components are still using Nuklear,
but the developer structures the GUI in a stateful manner, as known by other frontend 
libraries.
