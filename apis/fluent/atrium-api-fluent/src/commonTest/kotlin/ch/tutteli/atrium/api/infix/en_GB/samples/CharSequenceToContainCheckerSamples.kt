package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainCheckerSamples {

    @Test
    fun atLeast() {
        expect that "ABBC" contains o atLeast 2 value "B"

        fails {
            expect that "ABB" contains o atLeast 2 value "A"
        }
    }

    @Test
    fun atMost() {
        expect that "ABBC" contains o atMost 2 value "B"

        fails {
            expect that "AABBAA" contains o atMost 3 value "A"
        }
    }

    @Test
    fun notOrAtMost() {
        expect that "ABBC" contains o notOrAtMost 2 value "D"
        expect that "ABBC" contains o notOrAtMost 2 value "B"

        fails {
            expect that "AABBAA" contains o notOrAtMost 3 value "A"
        }
    }

    @Test
    fun butAtMost() {
        expect that "ABBC" contains o atLeast 1 butAtMost 2 value "B"

        fails {
            expect that "ABBBBCD" contains o atLeast 2 butAtMost 3 value "B"
        }
    }

    @Test
    fun exactly() {
        expect that "ABCBAC" contains o exactly 2 value "C"

        fails {
            expect that "ABBBBCD" contains o exactly 3 value "B"
        }
    }
}
