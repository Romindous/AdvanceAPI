package eu.endercentral.crazy_advancements.save;

import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.advancement.criteria.CriteriaType;

import java.util.List;

/**
 * Represents the Save Data for an Advancement saved by {@link CriteriaType} LIST
 *
 * @author Axel
 */
public record CriteriaData(NameKey name, List<String> criteria) {

	/**
	 * Constructor for creating CriteriaData
	 *
	 * @param name     The Unique Name of the Advancement
	 * @param criteria The Criteria that has been awarded
	 */
	public CriteriaData {}

	/**
	 * Gets the Unique Name of the Advancement
	 *
	 * @return The Unique Name
	 */
	@Override
	public NameKey name() {
		return name;
	}

	/**
	 * Gets the Criteria that has been awarded
	 *
	 * @return The Criteria
	 */
	@Override
	public List<String> criteria() {
		return criteria;
	}

}