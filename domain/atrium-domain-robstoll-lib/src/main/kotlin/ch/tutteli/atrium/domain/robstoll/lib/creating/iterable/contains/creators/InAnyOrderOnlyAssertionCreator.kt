package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.*

/**
 * Represents the base class for `in any order only` assertion creators and provides a corresponding template to fulfill
 * its responsibility.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 * @param SC The type of the search criteria.
 *
 * @property searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 *
 * @constructor Represents the base class for `in any order only` assertion creators and provides a corresponding
 *   template to fulfill its responsibility.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
abstract class InAnyOrderOnlyAssertionCreator<E, in T : Iterable<E?>, in SC>(
    private val searchBehaviour: InAnyOrderOnlySearchBehaviour
) : IterableContains.Creator<T, SC> {

    final override fun createAssertionGroup(plant: AssertionPlant<T>, searchCriteria: List<SC>): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val list = plant.subject.toMutableList()
            val actualSize = list.size
            val assertions = mutableListOf<Assertion>()

            val mismatches = createAssertionsForAllSearchCriteria(searchCriteria, list, assertions)
            val featureAssertions = createSizeFeatureAssertion(searchCriteria, actualSize)
            if (mismatches == 0 && list.isNotEmpty()) {
                featureAssertions.add(LazyThreadUnsafeAssertionGroup {
                    createExplanatoryGroupForMismatchesEtc(list, WARNING_ADDITIONAL_ENTRIES)
                })
            }
            assertions.add(AssertImpl.builder.feature
                .withDescriptionAndRepresentation(Untranslatable(list::size.name), RawString.create(actualSize.toString()))
                .withAssertions(featureAssertions)
                .build()
            )

            val description = searchBehaviour.decorateDescription(CONTAINS)
            val summary = AssertImpl.builder.summary
                .withDescription(description)
                .withAssertions(assertions)
                .build()

            if (mismatches != 0 && list.isNotEmpty()) {
                val warningDescription = when (list.size) {
                    mismatches -> WARNING_MISMATCHES
                    else -> WARNING_MISMATCHES_ADDITIONAL_ENTRIES
                }
                AssertImpl.builder.invisibleGroup
                    .withAssertions(
                        summary,
                        createExplanatoryGroupForMismatchesEtc(list, warningDescription)
                    )
                    .build()
            } else {
                summary
            }
        }
    }

    private fun createAssertionsForAllSearchCriteria(allSearchCriteria: List<SC>, list: MutableList<E?>, assertions: MutableList<Assertion>): Int {
        var mismatches = 0
        allSearchCriteria.forEach {
            val (found, assertion) = createAssertionForSearchCriterionAndRemoveMatchFromList(it, list)
            if (!found) ++mismatches
            assertions.add(assertion)
        }
        return mismatches
    }

    protected abstract fun createAssertionForSearchCriterionAndRemoveMatchFromList(searchCriterion: SC, list: MutableList<E?>): Pair<Boolean, Assertion>

    private fun createSizeFeatureAssertion(allSearchCriteria: List<SC>, actualSize: Int): MutableList<Assertion>
        = mutableListOf(AssertImpl.builder.descriptive
            .withTest { actualSize == allSearchCriteria.size }
            .withDescriptionAndRepresentation(
                DescriptionAnyAssertion.TO_BE,
                RawString.create(allSearchCriteria.size.toString())
            )
            .build()
        )

    private fun createExplanatoryGroupForMismatchesEtc(list: MutableList<E?>, warning: DescriptionIterableAssertion): AssertionGroup {
        val assertions = list.map { AssertImpl.builder.explanatory.create(it) }
        val additionalEntries = AssertImpl.builder.list
            .withDescriptionAndRepresentation(warning, RawString.EMPTY)
            .withAssertions(assertions)
            .build()
        return AssertImpl.builder.explanatoryGroup
            .withWarning
            .withAssertion(additionalEntries)
            .build()
    }
}
