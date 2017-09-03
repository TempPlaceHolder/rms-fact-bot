(ns rms-fact-bot.web
  (:require [compojure.core :refer :all]
            [clojure.string :as string]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]))

(def facts ["Richard Stallman takes notes in binary."
            "Richard Stallman is my shephurd, and I am his GNU."
            "Richard Stallman doesn't wget, Richard Stallman wdemands!"
            "Richard Stallman can touch MC Hammer."
            "Richard Stallman doesn't read web pages. They write to him."
            "Richard Stallman gets 9 bits to the byte."
            "Richard Stallman doesn't really believe in open software, because it's not free enough."
            "Richard Stallman is the only man alive who can pronounce GNU the way it is meant to be pronounced."
            "Some people check their computers for viruses. Viruses check their computers for Richard Stallman."
            "Richard Stallman memorises all his documents. In binary. He just types everything in whenever he needs a document."
            "When Richard Stallman makes a sudo command, he loses permissions."
            "Richard Stallman's beard is made of parentheses."
            "Richard Stallman's DNA is in binary."
            "Richard Stallman's nervous system is completely wireless."
            "Richard Stallman's brain accepts UNIX commands."
            "If Richard Stallman has 1GB of RAM, and if you have 1GB of RAM, Richard Stallman has more RAM than you."
            "Richard Stallman has a katana. 'Nuff said."
            "Richard Stallman wrote a program that divides by zero."
            "Richard Stallman's compiler is afraid to report errors."
            "Richard Stallman wrote the compiler God used. The Big Bang was the Universe's first segfault."
            "Richard Stallman successfully compiled a kernel of popcorn."
            "Richard Stallman doesn't write programs; they write themselves out of reverence."
            "Richard Stallman can make infinite loops end."
            "Richard Stallman's anti-virus programs cures HIV."
            "Richard Stallman's computer doesn't have a clock; it defines what time it is."
            "Richard Stallman wrote a program to compute the last digit of pi."
            "Richard Stallman doesn't use web browsers. He sends a link to a daemon that uses something like wget to fetch the page and sends it back to him."
            "Richard Stallman didn't 'write' Emacs or created it in his own image. Richard Stallman made Emacs an instance of himself."
            "Richard Stallman can coerce meaningful data from /dev/null."
            "Some people wear Linus Torvalds pyjamas to bed, Linus Torvalds wears Richard Stallman pyjamas."
            "There is no software development process, only a bunch of programs Richard Stallman allows to exist."
            "Richard Stallman's left and right hands are named ( and )."
            "Richard Stallman first words were actually syscalls."
            "When Richard Stallman pipes to more, he gets less."
            "Richard Stallman never showers; he runs 'make clean'."
            "Richard Stallman didn't write the GPL. He is the GPL."
            "Richard Stallman's pinky finger is really a USB memory stick."
            "In Soviet Russia, Richard Stallman is still Richard Stallman!"
            "Richard Stallman's flute only plays free music."
            "When Richard Stallman uses floats, there are no rounding errors."
            "Richard Stallman wrote a program so powerful it knows the question to 42."
            "Richard Stallman released his own DNA under GNU FDL."
            "Richard Stallman knows the entire wikipedia by heart, markup included."
            "Richard Stallman wrote the HAL9000 OS."
            "Richard Stallman doesn't code; he dares the computer to not do his bidding."
            "Global warming is caused by Richard Stallman's rage towards non-free software."
            "Richard Stallman doesn't evaluate expressions; expressions evaluate to Richard Stallman."
            "Richard Stallman can determine whether an arbitrary program will terminate."
            "Richard Stallman wrote the first version of Emacs on a typewriter."
            "Richard Stallman has no known weaknesses, except for a phobia against soap."
            "Richard Stallman is not affected by Godwin's Law."
            "Richard Stallman is licensed under GPL, so you can clone him and redistribute copies so you can help your neighbor. For example a version that take a bath more often."
            "Richard Stallman was coded by himself in lisp with Emacs."
            "Richard Stallman doesn't eat McDonald's because the machine that kills the cows uses proprietary software."
            "There is no chin behind Richard Stallman's legendary beard, there is only another Emacs."
            "In an average living room there are 1,242 objects Richard Stallman could use to write an OS, including the room itself."
            "Vendor lock-in is when vendors lock themselves inside of a building out of fear of Richard Stallman's wrath."
            "When Richard Stallman executes ps -e, you show up."
            "When Richard Stallman gets angry he doesn't swear; he recurses."
            "On Richard Stallman's computer the bootloader is contained in his .emacs."
            "Richard Satallman can make any operating system free, free from drivers."
            "Richard Stallman programmed Chuck Norris."
            "Richard Stallman's brain compiles and runs C code."
            "Richard Stallman wrote the first version of Emacs using Emacs."
            "Richard Stallman has no problem using Emacs. He wrote it with his 4 hands."
            "Richard Stallman's uptime is over 64 years. And counting up."
            "Richard Stallman will never die, but may some day go to /dev/null."
            "Richard Stallman is the One."
            "Richard Stallman can connect to any brain using an Emacs ssh client."
            "Richard Stallman ported Emacs to Intel 4004 chip."
            "Richard Stallman did not write GNU Emacs, he simply read the source code from /dev/null."
            "Richard Stallman programmed himself before he could even exist"
            "Richard Stallman can fill up /dev/null."
            "Richard Stallman is so zealous about privacy he has /dev/null as his home."
            "When Richard Stallman runs /bin/false, it returns 'true'."
            "Richard Stallman doesn't like money, because banks don't run on free software."
            "Richard Stallman user GNU tar to compress air."
            "Richard Stallman is just a guy who has strong principles and decided to follow them."
            "On the first day Richard Stallman said M-x create-light."
            "Richard Stallman once went out of scope for a while. The garbage collector never dared to touch him."
            "Richard Stallman does not compile; he closes his eyes, and see energy lines created between bit blocks by the compiler optimizations."
            "intx80 first calls Richard Stallman before calling sys_call."
            "Richard Stallman can telnet into Mordor."
            "sudo chown Richard:Stallman /all/your/base"
            "The R in RMS stands for RMS."
            "Richard Stallman can parse HTML with regular expressions."
            "In the beginning-of-buffer there was Richard Stallman."
            "RMS means 'RMS means Stallman'"
            "Richard Stallman wrote his own library and lives in it."
            "Richard Stallman doesn't sleep; he is compiling."
            "Richard Stallman compiled the first version of gcc with an hexadecimal editor."
            "Richard Stallman calculates the universe's entropy by exploiting forced stack overflows."
            "C is actually written in RMS."
            "There were no double rainbows before Richard Stallman."
            "RMS is Titanic."
            "Richard Stallman is the answer to the Turing Test."
            "for i = 1 to Stallman will never stop."
            "'RMS' stands for 'RMS Makes Software'."
            "Whenever someone writes a 'Hello, world' program, Richard Stallman says 'Hello' back."
            "Richard Stallman wasn't born. He was compiled from source."
            "Richard Stallman's toe cheese is aged to perfection."
            "Richard Stallman doesn't always run an OS kernel, but when he does he prefers GNU/Hurd. He is... the most interesting hacker in the world. Stay free, my friends."
            "When Richard Stallman gets hungry, he just picks debris from his foot and eats it."
            "Richard Stallman can GPL your code just by looking at."
            "Richard Stallman is so free that the primitive recursive function for computing his liberty causes a stack overflow."
            "GNU Hurd is taking more than twenty years to develop because Richard Stallman is using a programming language comprised entirely of different lengths of time."
            "Richard Stallman's beard contains Richard Stallman, whose beard contains Richard Stallman...."
            "Richard Stallman pipes the Emacs binaries to /dev/dsp before he goes to sleep."
            "When Richard Stallman counted his fingers as a kid, he always started with 0."
            "Richard Stallman's doesn't kill a process; he just dares it to stay running."
            "Richard Stallman exists because he compiled himself into being."
            "Richard Stallman's first words were in binary. When they couldn't understand him, he wrote a parser."
            "Richard Stallman doesn't need any codecs, he just opens a multimedia file with Emacs, and reads the bytes of the file as plain text. He then performs all the necessary decoding in his mind. But he refuses to decode files encrypted with DRM, although his mind is able to."])

(defn random-fact []
  (rand-nth facts))

(defn splash []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (random-fact)})

(defroutes app
  (GET "/" []
       (splash))
  (POST "/" []
        (string/join "" ["{\"color\":\"purple\", \"message\":\"Let's go to " "\", \"notify\":true, \"message_format\":\"text\"}"])))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
