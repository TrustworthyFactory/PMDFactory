package com.thalesgroup.optet.analysis.pmd;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;
import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RulePriority;
import net.sourceforge.pmd.RuleSet;
import net.sourceforge.pmd.RuleSetFactory;
import net.sourceforge.pmd.RuleSets;
import net.sourceforge.pmd.RuleViolation;
import net.sourceforge.pmd.lang.LanguageVersion;
import net.sourceforge.pmd.renderers.Renderer;
import net.sourceforge.pmd.util.datasource.DataSource;
import net.sourceforge.pmd.util.datasource.FileDataSource;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.osgi.framework.Bundle;

import com.thalesgroup.optet.common.data.OptetDataModel;
import com.thalesgroup.optet.common.data.Severity;
import com.thalesgroup.optet.twmanagement.Evidence;
import com.thalesgroup.optet.twmanagement.Metric;
import com.thalesgroup.optet.twmanagement.impl.OrchestrationImpl;




  import net.sourceforge.pmd.renderers.AbstractRenderer;
import net.sourceforge.pmd.renderers.Renderer;
import net.sourceforge.pmd.Report.ProcessingError;

  /**
 * PMDUtils pmd wrapper
 * 
 * @author F. Motte
 *
 */
public class PMDUtils  extends AuditToolUtil{

	private String targetJdk;
	private String language;
	private int minimumPriority = 5;


	/**
	 * constructor
	 * @param iProject The current project
	 * @param files the files to audit
	 */
	public PMDUtils(IProject iProject,  List<IFile> files) {
		super(iProject, files);

	}

	public static File resolveFile(String path,Bundle bundle) throws IOException {
		  File file=null;
		  if (bundle != null) {
		    URL url=FileLocator.find(bundle,new Path(path),Collections.emptyMap());
		    if (url != null) {
		      URL fileUrl=FileLocator.toFileURL(url);
		      try {
		        file=new File(fileUrl.toURI());
		      }
		 catch (      URISyntaxException e) {
		        e.printStackTrace();
		      }
		    }
		  }
		  return file;
		}


	/**
	 * getPMDConfiguration return the PMD configuration
	 * @return PMD configuration
	 */
	public  PMDConfiguration getPMDConfiguration()
	{
		PMDConfiguration configuration = new PMDConfiguration();
		LanguageVersion languageVersion = null;

		//		if ( null != targetJdk )
		//		{
		//			languageVersion = LanguageVersion.findByTerseName( "java " + targetJdk );
		//			if ( languageVersion == null )
		//			{
		//				PluginHelper.getInstance().logError("Unsupported targetJdk value '" + targetJdk + "'." );
		//			}
		//		}
		//		else if ( "javascript".equals( language ) || "ecmascript".equals( language ) )
		//		{
		//			languageVersion = LanguageVersion.ECMASCRIPT;
		//		}
		if ( languageVersion != null )
		{
			configuration.setDefaultLanguageVersion( languageVersion );
		}


		return configuration;
	}

	private void runSpecificAudit(String metric, String projectType){
		// get the current PMD configuration
		PMDConfiguration pmdConfiguration = getPMDConfiguration();
		RuleContext ruleContext = new RuleContext();


		RuleSetFactory ruleSetFactory = new RuleSetFactory();

		// set the ruleset define by the user
		String ruleSetsPath = "";

		if (projectType.equals("java")){

			if (metric.equals("SecurityMetric")){
//				Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
//				URL fileURL = bundle.getEntry("resources/security.xml");
//				String file = null;
//				try {
//					file = FileLocator.resolve(fileURL).toURI().toString();
//				} catch (URISyntaxException e1) {
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				ruleSetsPath = file;
//				OptetDataModel.getInstance().configureRulesMetric(metric, 2);

			} else if (metric.equals("SoftwareComplexityMetric")){
//				Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
//				URL fileURL = bundle.getEntry("resources/complexity.xml");
//				String file = null;
//				try {
//					file = FileLocator.resolve(fileURL).toURI().toString();
//				} catch (URISyntaxException e1) {
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				ruleSetsPath = file;
//
//				OptetDataModel.getInstance().configureRulesMetric(metric, 11);

			}else if (metric.equals("interceptet errors")){
				Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
				URL fileURL = bundle.getEntry("resources/Exception.xml");
				String file = null;
				try {
					file = FileLocator.resolve(fileURL).toURI().toString();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				if (file==null)
					try {
						file= PMDUtils.resolveFile("resources/Exception.xml", bundle).toString();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				ruleSetsPath = file;
				OptetDataModel.getInstance().configureRulesMetric(metric, 12);

			}else if (metric.equals("Compliance with best programing practices")){
				Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
				URL fileURL = bundle.getEntry("resources/CodingStyle.xml");
				String file = null;
				try {
					file = FileLocator.resolve(fileURL).toURI().toString();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (file==null)
					try {
						file= PMDUtils.resolveFile("resources/CodingStyle.xml", bundle).toString();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				ruleSetsPath = file;
				OptetDataModel.getInstance().configureRulesMetric(metric, 36);

			}
		}else if (projectType.equals("android")){
			if (metric.equals("SecurityMetric")){
//				Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
//				URL fileURL = bundle.getEntry("resources/securityAndroid.xml");
//				String file = null;
//				try {
//					file = FileLocator.resolve(fileURL).toURI().toString();
//				} catch (URISyntaxException e1) {
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				ruleSetsPath = file;
//				OptetDataModel.getInstance().configureRulesMetric(metric, 2);

			} else if (metric.equals("SoftwareComplexityMetric")){
//				Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
//				URL fileURL = bundle.getEntry("resources/complexityAndroid.xml");
//				String file = null;
//				try {
//					file = FileLocator.resolve(fileURL).toURI().toString();
//				} catch (URISyntaxException e1) {
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				ruleSetsPath = file;
//
//				OptetDataModel.getInstance().configureRulesMetric(metric, 11);

			}else if (metric.equals("interceptet errors")){
				Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
				URL fileURL = bundle.getEntry("resources/AndroidException.xml");
				String file = null;
				try {
					file = FileLocator.resolve(fileURL).toURI().toString();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (file==null)
					try {
						file= PMDUtils.resolveFile("resources/AndroidException.xml", bundle).toString();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				ruleSetsPath = file;
				OptetDataModel.getInstance().configureRulesMetric(metric, 12);

			}else if (metric.equals("Compliance with best programing practices")){
				Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
				URL fileURL = bundle.getEntry("resources/AndroidCodingStyle.xml");
				String file = null;
				try {
					file = FileLocator.resolve(fileURL).toURI().toString();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (file==null)
					try {
						file= PMDUtils.resolveFile("resources/AndroidCodingStyle.xml", bundle).toString();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				ruleSetsPath = file;
				OptetDataModel.getInstance().configureRulesMetric(metric, 37);

			}	
		}



		System.out.println("ruleset : " + ruleSetsPath + "   for metric " + metric);

		pmdConfiguration.getClassLoader();
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);

			URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{
					bundle.getEntry("resources/Exception.xml"),
					bundle.getEntry("resources/CodingStyle.xml"),
					bundle.getEntry("resources/AndroidException.xml"),
					bundle.getEntry("resources/AndroidCodingStyle.xml")},pmdConfiguration.getClassLoader()); 

		pmdConfiguration.setClassLoader(urlClassLoader);
		pmdConfiguration.setRuleSets(ruleSetsPath);

		// create DataSource from the file list (specific to PMD)

		Iterator<IFile> iter = getIFileList().iterator();
		while (iter.hasNext()) {
			try
			{
				IFile elem = (IFile) iter.next();
				List<DataSource> dataSources = new ArrayList<DataSource>();
				dataSources.add( new FileDataSource( new File(elem.getLocationURI()) ) );



//				List<Renderer> renderers = Collections.emptyList();
//
//				// Unfortunately we need to disable multi-threading for now - as otherwise our PmdReportListener
//				// will be ignored.
//				// Longer term solution could be to use a custom renderer instead. And collect with this renderer
//				// all the violations.
//				pmdConfiguration.setThreads( 0 );
//				// run PMD
//				PMD.processFiles( pmdConfiguration, ruleSetFactory, dataSources, ruleContext, renderers );
//
//				// transfer the report to the optet model
//				pmdToOptet(ruleContext.getReport(), metric);
//				 
				///////////////////////////////////////////////////////
				 List<Report> fileReports = new ArrayList<Report>();
				PMD.processFiles(pmdConfiguration, ruleSetFactory, dataSources, ruleContext,
						Collections.<Renderer> singletonList(new GetReportRenderer(fileReports)));

				// combine multiple reports to one
				Report aggregate = new Report();
				for (Report r : fileReports) {
					Iterator<RuleViolation> iter3 = r.iterator();
					while (iter3.hasNext()) {
						aggregate.addRuleViolation(iter3.next());
					}
					Iterator<ProcessingError> iter2 = r.errors();
					while (iter2.hasNext()) {
						aggregate.addError(iter2.next());
					}
				}




				// transfer the report to the optet model
				pmdToOptet(aggregate, metric);

			}
			catch ( Exception e )
			{
				PluginHelper.getInstance().logException(e,e.getMessage());
			}
		}
	}

	
	
	   private static class GetReportRenderer extends AbstractRenderer {
		    
		            private List<Report> reports = new ArrayList<Report>();
		    
		            public GetReportRenderer(List<Report> reports) {
		                super("GetReportRenderer", "Extracts report objects from multi-threaded PMD execution");
		                this.reports = reports;
		            }
		    
		            @Override
		            public String defaultFileExtension() {
		                return null;
		            }
		    
		            @Override
		            public void start() throws IOException {
		            }
		    
		            @Override
		            public void startFileAnalysis(DataSource dataSource) {
		            }
		    
		            @Override
		            public synchronized void renderFileReport(Report report) throws IOException {
		                this.reports.add(report);
		            }
		    
		            @Override
		            public void end() throws IOException {
		            }
		    
		        }
	/**
	 * Runs PMD on the specific file with the specifc rule.
	 */
	public void runAudit(IProject project, List<Evidence> evidences, String projectType) throws Exception {
		for (Iterator iterator = evidences.iterator(); iterator.hasNext();) {
			Evidence evidence = (Evidence) iterator.next();
			Map<String, Metric> metrics = evidence.getMetrics();
			for(Entry<String, Metric> entry : metrics.entrySet()){
				String key = entry.getKey();
				System.out.println("run ********** "  + key);
				runSpecificAudit( key, projectType);
			}
		}
		OrchestrationImpl.getInstance().staticAnalyseFinished(Activator.PLUGIN_ID);

	}


	/**
	 * pmdToOptet translate the PMD report to the Optet data model
	 * @param report the pmd report
	 * @param evidenceName 
	 */
	private void pmdToOptet (Report report, String evidenceName)
	{
		if (!report.isEmpty()) {
			for (Iterator<RuleViolation> i = report.iterator(); i.hasNext(); ) {
				final RuleViolation viol = (RuleViolation)i.next();
				//Use viol to retrieve the details of the errror

				// add a market into the file
				//addMarker(getFile(viol.getFilename()), viol.getRule().getMessage(), viol.getBeginLine(), getSeverity(viol.getRule().getPriority()));

				// add a error entry into the optet model
				System.out.println("adderror" + " pmd " + evidenceName + " " + viol.getRule().getMessage()+ " " + viol.getRule().getName()+ " " + viol.getRule().getName());
				OptetDataModel.getInstance().addError(getFile(viol.getFilename()),
						"pmd",
						viol.getBeginLine(),
						getSeverity(viol.getRule().getPriority()),
						evidenceName,
						viol.getRule().getMessage(),
						viol.getDescription(),
						viol.getRule().getName());
			}
		}


	}

	/**
	 * getSeverity transalte the PMD severity into the optet severity
	 * @param severity the pmd severity
	 * @return the severity 
	 */
	private Severity getSeverity(RulePriority severity)
	{
		Severity sev = null;


		switch (severity) {
		case HIGH:
			sev = Severity.HIGH;
			break;
		case MEDIUM_HIGH:
		case MEDIUM:
		case MEDIUM_LOW:
			sev = Severity.MEDIUM;
			break;
		case LOW:
			sev = Severity.LOW;
			break;

		default:
			sev=Severity.HIGH;
			break;
		}

		return sev;
	}


	@Override
	public void runMetric(IProject project) throws Exception {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.analysis.pmd.AuditToolUtil#runRuntimeAnalysis()
	 */
	@Override
	public void runRuntimeAnalysis(IProject project) throws Exception {
		// TODO Auto-generated method stub

	}

}
