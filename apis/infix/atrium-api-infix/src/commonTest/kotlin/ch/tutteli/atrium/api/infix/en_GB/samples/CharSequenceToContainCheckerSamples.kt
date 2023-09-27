package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceToContainCheckerSamples {

    @Test
    fun atLeast() {
        expect("ABBC") contains o atLeast 2 value "B"

        failsExpect {
            expect("ABB") contains o atLeast 2 value "A"
        }
    }

    @Test
    fun atMost() {
        expect("ABBC") contains o atMost 2 value "B"

        failsExpect {
            expect("AABBAA") contains o atMost 3 value "A"
        }
    }

    @Test
    fun notOrAtMost() {
        expect("ABBC") containsNotOrAtMost 2 value "D"
        expect("ABBC") containsNotOrAtMost 2 value "B"

        failsExpect {
            expect("AABBAA") containsNotOrAtMost 3 value "A"
        }
    }

    @Test
    fun butAtMost() {
        expect("ABBC") contains o atLeast 1 and atMost 2 value "B"

        failsExpect {
            expect("ABBBBCD") contains o atLeast 2 and atMost 3 value "B"
        }
    }

    @Test
    fun exactly() {
        expect("ABCBAC") contains exactly 2 value "C"

        failsExpect {
            expect("ABBBBCD") contains exactly 3 value "B"
        }
    }
}