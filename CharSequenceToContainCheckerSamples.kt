import ch.tutteli.atrium.api.fluent.en_GB.atExactly
import ch.tutteli.atrium.api.fluent.en_GB.atLeast
import ch.tutteli.atrium.api.fluent.en_GB.atMost
import ch.tutteli.atrium.api.fluent.en_GB.butAtMost
import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.notOrAtMost
import ch.tutteli.atrium.api.fluent.en_GB.the
import ch.tutteli.atrium.api.fluent.en_GB.value
import ch.tutteli.atrium.api.fluent.en_GB.values
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class CharSequenceToContainCheckerSamples {

    @Test
    fun atLeast() {
        expect("hello world and hello reader") has o atLeast 2 the value ("hello")
        
        fails {
            expect("hello world and hello reader") has o atLeast 3 the value ("hello")
        }
    }
    
    @Test
    fun atMost() {
        expect("hello world and hello reader") has o atMost 2 the value ("hello")

    }
    
    @Test
    fun exactly() {
        expect("hello world and hello reader") has o exactly 2 the value ("hello")
    }
    
    @Test
    fun notOrAtMost() {
        expect("hello world and hello reader") has o notOrAtMost 1 the value ("not in there")
    }
    
    @Test
    fun butAtMost() {
        expect("hello world and hello reader") has o atLeast 1 butAtMost 2 the values ("hello", "world")
    }
}