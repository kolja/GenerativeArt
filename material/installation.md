# How to install shadow-cljs?

The command to install shadow-cljs globally is:

```
npm install -g shadow-cljs
```

Also, there are detailed installation instructions on [Github](https://shadow-cljs.github.io/docs/UsersGuide.html#_standalone_via_code_npm_code)

### How to verify the install was successful?

to make sure that the installation was successful, you can clone this very GenerativeArt repository and run the watch task like so:

```
	git clone https://github.com/kolja/GenerativeArt.git

	cd GenerativeArt

	npm install

	shadow-cljs watch sketch
```

Then, when you open a browser and point it to [http://localhost:8080](http://localhost:8080)

You should see a red square on a yellow background.

### Troubleshooting

chances are you’ll see some error-messages during the installation / verification process.
It may be that those are related to the java environment not being compatible.

When I run

```
	java -version
```

on my machine, I get the following output:

```
	openjdk version "20.0.1" 2023-04-18
	OpenJDK Runtime Environment Homebrew (build 20.0.1)
	OpenJDK 64-Bit Server VM Homebrew (build 20.0.1, mixed mode, sharing)
```

to achieve this, I ran

```
	brew install openjdk@20
```

and also set an environment-variable in my shelll rc like so:

```
	export JAVA_HOME='/usr/local/opt/openjdk@20'

```

For reference:

The shadow-cljs repository is here: https://github.com/thheller/shadow-cljs
the users-guide can be found here: https://shadow-cljs.github.io/docs/UsersGuide.html
