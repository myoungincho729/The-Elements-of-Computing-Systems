// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

//Pseudo Code
//key == 0 -> to white screen
//key != 0 -> to black screen
(LOOP)
    @KBD
    D=M
    @WHITE
    D;JEQ
    @BLACK
    0;JMP


(WHITE)
    @SCREEN
    D=A
    @addr
    M=D

    (LOOPWHITE)
        @addr
        D=M
        @KBD
        D=D-A
        @LOOP
        D;JEQ
        @addr
        A=M
        M=0
        @addr
        M=M+1
        @LOOPWHITE
        0;JMP

(BLACK)
    @SCREEN
    D=A
    @addr
    M=D

    (LOOPBLACK)
        @addr
        D=M
        @KBD
        D=D-A
        @LOOP
        D;JEQ
        @addr
        A=M
        M=-1
        @addr
        M=M+1
        @LOOPBLACK
        0;JMP

