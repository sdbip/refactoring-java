# Refactoring Java

The code creates an information slip about movie rentals.
Rewrite and improve the code after your own liking.

Think: you are responsible for the solution, this is a solution you will have to put your name on.


## Handing in the assignment

Reason how you have been thinking and the decisions you took. 
You can hand in the result any way you feel (pull-request, own fork or ZIP-file)
Note: the Git history must be included.


## To run the test:

```
cd src
javac Main.java
java Main
```

## My final thoughts

Maybe I should have converted the entire thing to a Maven project or something, but then I
feel it would not be a *refactoring* test. It would be a *technology* test, and that's not
how the task was specified.

There is no clear “external behaviour” to this code. There is no part of the code that can
be considered an application, and here is no mention of how it might be used as a library
by some other app. I decided to define my boundary/external user as being the Main class. To
be able to call this “refactoring,” I disallowed changing the invocation of the Main class,
and also its containing code. Everything else could change. (Though I did add a package that
has to be imported, and I also instantiate the HardCodedMovieRepository class in Main, so I
suppose I failed that.)

I focused on trying to separate what I considered implementation details (the `details`
package) from the domain model (`rentals`). There is some business logic still in the
`RentalInfo` class, and it should be moved into a business package, but I don't know exactly
how right now. I think I would need to have a context for how this will be used in a “real”
situation as a guide.
