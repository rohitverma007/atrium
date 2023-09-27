package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainCreatorSamples {

    @Test
    fun value() {
        expect("ABC") toContain o exactly 1 value "A"
        expect("ABBBC") toContain o atLeast 2 value "B"

        fails {
            expect("AAAAAA") toContain o atMost 3 value "A"
        }
    }

    @Test
    fun valueIgnoringCase() {
        expect("ABC") toContain ignoringCase o value("a")
        expect("AbbbC") toContain ignoringCase o value("B")

        fails {
            expect("AAAAAA") toContain ignoringCase o value("B")
        }
    }

    @Test
    fun valueIgnoringCaseWithChecker() {
        expect("ABC") toContain ignoringCase o exactly 1 value "A"
        expect("AAABBC") toContain ignoringCase o atMost 3 value "b"
        expect("aBBBCD") toContain ignoringCase o atLeast 1 value "A"

        fails {
            expect("AAAAAABBBB") toContain ignoringCase o atMost 3 value "A"
        }
        fails {
            expect("AAABBBb") toContain ignoringCase o exactly 3 value "b"
        }
        fails {
            expect("AAAAAABBBB") toContain ignoringCase o atLeast 3 value "D"
        }
    }

    @Test
    fun values() {
        expect("ABC") toContain o exactly 1 values("A", "B", "C")
        expect("AAABC") toContain o atMost 3 values("A", "B", "C")
        expect("ABBBCD") toContain o atLeast 1 values("A", "B", "C", "D")

        fails {
            expect("AAAAAABBBB") toContain o atMost 3 values("A", "B")
        }
        fails {
            expect("AAABBBB") toContain o exactly 3 values("A", "B")
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast 3 values("A", "B", "C")
        }
    }

    @Test
    fun valuesIgnoringCase() {
        expect("AbC") toContain ignoringCase o values("A", "B", "c")

        fails {
            expect("aabaabbb") toContain ignoringCase o values("A", "B", "C")
        }
    }

    @Test
    fun valuesIgnoringCaseWithChecker() {
        expect("ABc") toContain ignoringCase o exactly 1 values("A", "b", "C")
        expect("AaaBC") toContain ignoringCase o atMost 3 values("A", "B", "c")
        expect("ABBBcD") toContain ignoringCase o atLeast 1 values("a", "b", "C", "d")

        fails {
            expect("AAAAAABBBB") toContain ignoringCase o atMost 3 values("a", "b")
        }
        fails {
            expect("AAABBBB") toContain ignoringCase o exactly 3 values("A", "b")
        }
        fails {
            expect("AAAAAABBBB") toContain ignoringCase o atLeast 3 values("a", "b", "C")
        }
    }

    @Test
    fun regex() {
        expect("ABC") toContain o exactly 1 matchFor "A", "B", "C"
        expect("AAABC") toContain o atMost 3 matchFor "A", "B", "C"
        expect("ABBBCD") toContain o atLeast 1 matchFor "A", "B", "C", "D"

        fails {
            expect("AAAAAABBBB") toContain o atMost 3 matchFor "A", "B"
        }
        fails {
            expect("AAABBBB") toContain o exactly 3 matchFor "A", "B"
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast 3 matchFor "A", "B", "C"
        }
    }

    @Test
    fun regexIgnoringCase() {
        expect("AbC") toContain ignoringCase o matchFor "A", "B", "c"

        fails {
            expect("aabaabbb") toContain ignoringCase o matchFor "A", "B", "C"
        }
    }

    @Test
    fun regexIgnoringCaseWithChecker() {
        expect("ABc") toContain ignoringCase o exactly 1 matchFor "A", "b", "C"
        expect("AaaBC") toContain ignoringCase o atMost 3 matchFor "A", "B", "c"
        expect("ABBBcD") toContain ignoringCase o atLeast 1 matchFor "a", "b", "C", "d"

        fails {
            expect("AAAAAABBBB") toContain ignoringCase o atMost 3 matchFor "a", "b"
        }
        fails {
            expect("AAABBBB") toContain ignoringCase o exactly 3 matchFor "A", "b"
        }
        fails {
            expect("AAAAAABBBB") toContain ignoringCase o atLeast 3 matchFor "a", "b", "C"
        }
    }

    @Test
    fun matchFor() {
        expect("ABC") toContain o exactly(1).matchFor(Regex("A"), Regex("B"), Regex("C"))
        expect("AAABC") toContain o atMost(3).matchFor(Regex("A"), Regex("B"), Regex("C"))
        expect("ABBBCD") toContain o atLeast(1).matchFor(Regex("A"), Regex("B"), Regex("C"), Regex("D"))

        fails {
            expect("AAAAAABBBB") toContain o atMost(3).matchFor(Regex("A"), Regex("B"))
        }
        fails {
            expect("AAABBBB") toContain o exactly(3).matchFor(Regex("A"), Regex("B"))
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast(3).matchFor(Regex("A"), Regex("B"), Regex("C"))
        }
    }


    @Test
    fun elementsOf() {
        expect("ABC") toContain o exactly(1).elementsOf(listOf("A", "B", "C"))
        expect("AAABC") toContain o atMost(3).elementsOf(listOf("A", "B", "C"))
        expect("ABBBCD") toContain o atLeast(1).elementsOf(listOf("A", "B", "C", "D"))

        fails {
            expect("AAAAAABBBB") toContain o atMost(3).elementsOf(listOf("A", "B"))
        }
        fails {
            expect("AAABBBB") toContain o exactly(3).elementsOf(listOf("A", "B"))
        }
        fails {
            expect("AAAAAABBBB") toContain o atLeast(3).elementsOf(listOf("A", "B", "C"))
        }
    }

    @Test
    fun elementsOfIgnoreCase() {
        expect("AbC") toContain ignoringCase o elementsOf(listOf("A", "B", "c"))

        fails {
            expect("aabaabbb") toContain ignoringCase o elementsOf(listOf("A", "B", "C"))
        }
    }

    @Test
    fun elementsOfIgnoringCaseWithChecker() {
        expect("ABc") toContain ignoringCase o exactly(1).elementsOf(listOf("A", "b", "C"))
        expect("AaaBC") toContain ignoringCase o atMost(3).elementsOf(listOf("A", "B", "c"))
        expect("ABBBcD") toContain ignoringCase o atLeast(1).elementsOf(listOf("a", "b", "C", "d"))

        fails {
            expect("AAAAAABBBB") toContain ignoringCase o atMost(3).elementsOf(listOf("a", "b"))
        }
        fails {
            expect("AAABBBB") toContain ignoringCase o exactly(3).elementsOf(listOf("A", "b"))
        }
        fails {
            expect("AAAAAABBBB") toContain ignoringCase o atLeast(3).elementsOf(listOf("a", "b", "C"))
        }
    }
}
