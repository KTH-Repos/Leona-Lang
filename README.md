# Leona-Lang

This is an implementation for a programming language used for graphics. A user should be able to instruct Leona with the help of this language. The technical specification are specified below:

We have a turtle (let's call it Leona) with a pen. We can instruct Leona to go
to different places and lines are drawn as along the path Leona walks. The instructions for Leona are given as "program"
in the Leona language. The language has the following instruction set:

- FORW d Leona moves forward d points (for a positive integer d).
- BACK d Leona goes back d points (for a positive integer d).
- LEFT θ Leona turns left θ degrees (for a positive integer θ), without moving
from its current position.
- RIGHT θ Leona turns right θ degrees (for a positive integer θ), without moving from
its current position.
- DOWN Leona lowers the pen so that it leaves a trail when Leona moves
UP Leona raises the pen so that no trace is left when Leona moves
- COLOR c changes the color of the pen to the color c. Color is specified in hex format, e.g. #FFA500
for orange (if you're not sure what this means, your favorite search engine
your friend, as usual).
- REP r <REPS> Leona repeats <REPS> r times (for a positive integer r). <REPS> is one
sequence of one or more instructions, surrounded by quotation marks (‘"’). If
the sequence only consists of a single instruction, the quotation marks are optional.

The language is case insensitive – both command names and descriptions of colors can be lowercase and uppercase
letters are mixed up. Commands in the language end with period (‘.’), except for REP commands, after these you have 
no periods (on the other hand, every command in the REP sequence must end with a period).
Comments can be written in the language with a percent sign ('%'), anything after a percent sign on
a line is considered a comment. All whitespace (spaces, tabs and newline characters) is equivalent
except in comments (where newline means "end of comment"). There must be whitespace between
a command and its parameter (eg between RIGHT and θ), as well as between the r argument to REP and
The <REPS> part. Otherwise, all whitespace is arbitrary.
Leona starts at the position (0, 0) and faces in the direction of the point (1, 0). The pen is initially blue
(#0000FF) and in raised position.
Note that even if all input parameters are integers, Leona may end up at coordinates that are not integers.
If we e.g. from the starting position perform LEFT 30. FORW 2. Leona will be at the position
(√3, 1) ≈ (1.732, 1).


