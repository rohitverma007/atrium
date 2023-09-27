package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainCheckerSamples {

    @Test
    fun atLeast() {
        expect("ABBC") contains o atLeast 2 value "B"

        fails {
            expect("ABB") contains o atLeast 2 value "A"
        }
    }

    @Test
    fun atMost() {
        expect("ABBC") contains o atMost 2 value "B"

        fails {
            expect("AABBAA") contains o atMost 3 value "A"
        }
    }

    @Test
    fun notOrAtMost() {
        expect("ABBC") contains o notOrAtMost 2 value "D"
        expect("ABBC") contains o notOrAtMost 2 value "B"

        fails {
            expect("AABBAA") contains o notOrAtMost 3 value "A"
        }
    }

    @Test
    fun butAtMost() {
        expect("ABBC") contains o atLeast 1 butAtMost 2 value "B"

        fails {
            expect("ABBBBCD") contains o atLeast 2 butAtMost 3 value "B"
        }
    }

    @Test
    fun exactly() {
        expect("ABCBAC") contains o exactly 2 value "C"

        fails {
            expect("ABBBBCD") contains o exactly 3 value "B"
        }
    }
}
