package eu.endercentral.crazy_advancements.save;

import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.advancement.criteria.CriteriaType;

/**
 * Represents the Save Data for an Advancement saved by {@link CriteriaType} NUMBER
 *
 * @author Axel
 */
public record ProgressData(NameKey name, int progress) {

	/**
	 * Constructor for creating ProgressData
	 *
	 * @param name     The Unique Name of the Advancement
	 * @param progress The Progress
	 */
	public ProgressData {}

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
	 * Gets the Progress
	 *
	 * @return The Progress
	 */
	@Override
	public int progress() {
		return progress;
	}

}