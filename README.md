
# Huffman compressor

Tool for compressing text files, utilizing the Huffman Algorythm.



## Installation

#### Using jar

Clone this repo. The .jar will be already present.

```bash
  git clone https://github.com/Namlev1/huffman
  cd huffman
```

#### Build jar using IntelliJ Idea

Clone this repo.

```bash
  git clone https://github.com/Namlev1/huffman
  cd huffman
```

Open the project with IntelliJ Idea and wait until all the dependencies are downloaded. Add a new artifact in *Project Structure -> Artifacts -> Add jar (from modules with dependencies...)*. Select Huffman.java as main class. Build this artifact using *Build -> Build artifacts*.

## Usage/Examples

The program is used with command:

```bash
  java -jar Huffman.jar
```

No flag:
```
Error: wrong call
Usage:
java Huffman.java <option> <filename>

Options:
-c compress
-d decompress
-h help
```

Select path after *-c* flag to compress the file. Compressed files have *.cmp* extension.

```
java -jar Huffman.jar -c aaa.txt
File compressed successfully
```
Select path after *-d* flag to decompress the file.
```
java -jar Huffman.jar -d aaa.cmp
File decompressed successfully
```