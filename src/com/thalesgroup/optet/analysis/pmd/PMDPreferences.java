package com.thalesgroup.optet.analysis.pmd;



/**
 * The PMD preference access
 * @author F. Motte
 *
 */
public class PMDPreferences{


	/**
	 * isPmdUsed return true if PMD must be used from the preference choice
	 * @return true if PMD must be used from the preference choice
	 */
	public boolean isPmdUsed() {
		return true;
	}

//	/**
//	 * getConfigurationFile get the PMD configuration pile
//	 * @return the configuration file
//	 */
//	public String getConfigurationFile () {
//		return Platform.getPreferencesService().getString(Activator.PLUGIN_ID, PreferenceConstants.PMD_CONF_PATH, "", null);
//	}
	
	/**
	 * getCategoryList return the list of category selected into the preference page
	 * @return list of category
	 */
	public String getCategoryList ()
	{
		return "";
	}
}
