/*******************************************************************************
 * Copyright (C) 2015  ORO e ISMB
 * Questo e' il main del programma di ottimizzazione VRPTW
 * Il programma prende in input un file csv con i clienti ed un csv di configurazione (deposito e veicoli) e restituisce in output il file delle route ed un file sintetico di dati dei viaggi.
 * L'algoritmo ultizzato e' un Large Neighborhood 
 ******************************************************************************/

package main;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.xml.sax.SAXException;

import generatedJAXB.*;
import generatedJAXB.Algorithm.Construction;
import generatedJAXB.Algorithm.Strategy;
import generatedJAXB.Algorithm.Strategy.SearchStrategies;
import generatedJAXB.InsertionType.ConsiderFixedCosts;
import generatedJAXB.SearchStrategyType.Modules;
import jsprit.core.algorithm.VehicleRoutingAlgorithm;
import jsprit.core.algorithm.io.VehicleRoutingAlgorithms;
import jsprit.core.algorithm.selector.SelectBest;
import jsprit.core.algorithm.termination.TimeTermination;
import jsprit.core.problem.VehicleRoutingProblem;
import jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import jsprit.instance.reader.SolomonReader;
import jsprit.util.Examples;
import main.OROoptions.CONSTANTS;
import main.OROoptions.PARAMS;

public class Main {
			
	public static void main(String[] args) {
		/*// Some preparation - create output folder
		//System.out.println("start");
		Examples.createOutputFolder();
		
		// Read input parameters
		OROoptions options = new OROoptions(args);
		
		for(int r=0; r<(int)options.get(CONSTANTS.REPETITION); r++) {
			// Time tracking
			long startTime = System.currentTimeMillis();
			// Create a vrp problem builder
			VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
			// A solomonReader reads solomon-instance files, and stores the required information in the builder.
			new SolomonReader(vrpBuilder).read("input/" + options.get(PARAMS.INSTANCE));
			VehicleRoutingProblem vrp = vrpBuilder.build();
			// Create the instace and solve the problem
			VehicleRoutingAlgorithm vra = VehicleRoutingAlgorithms.readAndCreateAlgorithm(vrp, 
					(int)options.get(CONSTANTS.THREADS), (String)options.get(CONSTANTS.CONFIG));
			setTimeLimit(vra, (long)options.get(CONSTANTS.TIME));
			
			// Solve the problem
			Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
			// Extract the best solution
			VehicleRoutingProblemSolution solution = new SelectBest().selectSolution(solutions);
									
			// Print solution on a file
			OROutils.write(solution, (String)options.get(PARAMS.INSTANCE), System.currentTimeMillis()-startTime, (String)options.get(CONSTANTS.OUTPUT));
			// Print solution on the screen (optional)
			//SolutionPrinter.print(vrp, solution, SolutionPrinter.Print.VERBOSE);
			// Draw solution on the screen (optional)
			//new GraphStreamViewer(vrp, solution).labelWith(Label.ID).setRenderDelay(10).display();
			//System.out.println("end");
		}*/
		
		try {
			//tryAllConfigurations(args);
			tryOneConfiguration(args);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			System.err.println("Exception while marshalling xml file");
			e.printStackTrace();
			System.exit(1);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.err.println("Exception while marshalling xml file");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static void setTimeLimit(VehicleRoutingAlgorithm vra, long timeMilliSec) {
		TimeTermination tterm = new TimeTermination(timeMilliSec);
		vra.setPrematureAlgorithmTermination(tterm);
		vra.addListener(tterm);
	}
	
	public static void tryAllConfigurations(String[] args) throws JAXBException, SAXException 
	{
		ObjectFactory of = new ObjectFactory();
		
		//create algorithm
		Algorithm alg = of.createAlgorithm();
		alg.setIterations(BigInteger.valueOf(1024));
		InsertionTypeEnum insTypeEnum = InsertionTypeEnum.BEST_INSERTION;
		InsertionType insertion = of.createInsertionType();
		insertion.setName(insTypeEnum);
		ConsiderFixedCosts consFixed = of.createInsertionTypeConsiderFixedCosts();
		consFixed.setValue("true");
		consFixed.setWeight(1.0);
		Construction construction = of.createAlgorithmConstruction();
		construction.setInsertion(insertion);
		alg.setConstruction(construction);
		
		//build strategy
		Strategy strategy = of.createAlgorithmStrategy();
		strategy.setMemory(BigInteger.valueOf(1));
		alg.setStrategy(strategy);
		SearchStrategies searchStrategies = of.createAlgorithmStrategySearchStrategies();
		strategy.setSearchStrategies(searchStrategies);
		
		//try the search strategy with 2 search strategies
		SearchStrategyType searchStrategy1 = of.createSearchStrategyType();
		searchStrategy1.setName("strategy_1");
		searchStrategies.getSearchStrategy().add(searchStrategy1);
		SearchStrategyType searchStrategy2 = of.createSearchStrategyType();
		searchStrategy2.setName("strategy_2");
		searchStrategies.getSearchStrategy().add(searchStrategy2);
		
		List<SelectorType> list = new ArrayList<SelectorType>();
		SelectorType st = of.createSelectorType();
		st.setName(SelectorTypeEnum.SELECT_BEST);
		list.add(st);
		st = of.createSelectorType();
		st.setName(SelectorTypeEnum.SELECT_RANDOMLY);
		list.add(st);
		
		for (SelectorType s1 : list)
		{
			for (SelectorType s2 : list)
			{
				searchStrategy1.setSelector(s1);
				searchStrategy2.setSelector(s2);
				
				/* 
				 * probability = 0.0 means try all the combinations
				 * starting from 0.2: try only (0.2,0.8) (0.3,0.7) (0.4,0.6) (0.5,0.5)   
				 */
				double probability = 0.2;  
				for ( ; probability <= 0.5 ; probability += 0.1 )
				{
					
					searchStrategy1.setProbability(probability);
					searchStrategy2.setProbability(1-probability);
					
					List<AcceptorType> accList = new ArrayList<AcceptorType>();
					AcceptorType at = of.createAcceptorType();
					at.setName(AcceptorTypeEnum.ACCEPT_NEW_REMOVE_FIRST);
					accList.add(at);
					at = of.createAcceptorType();
					at.setName(AcceptorTypeEnum.ACCEPT_NEW_REMOVE_WORST);
					accList.add(at);
					at = of.createAcceptorType();
					at = of.createAcceptorType();
					at.setName(AcceptorTypeEnum.SCHRIMPF_ACCEPTANCE);
					accList.add(at);
					at.setName(AcceptorTypeEnum.EXPERIMENTAL_SCHRIMPF_ACCEPTANCE);
					accList.add(at);
					at = of.createAcceptorType();
					at.setName(AcceptorTypeEnum.GREEDY_ACCEPTANCE);
					accList.add(at);
					at = of.createAcceptorType();
					at.setName(AcceptorTypeEnum.GREEDY_ACCEPTANCE_MIN_VEH_FIRST);
					accList.add(at);
					
					
					for ( Iterator<AcceptorType> it1 = accList.iterator(); it1.hasNext(); )
					{
						AcceptorType a1 = it1.next();
						a1.setAlpha(0.0);
						//a1.setInitialThreshold(0.0);
						a1.setWarmup(0);
						
						searchStrategy1.setAcceptor(a1);
						for ( Iterator<AcceptorType> it2 = accList.iterator(); it2.hasNext(); )
						{
							AcceptorType a2 = it2.next();
							searchStrategy2.setAcceptor(a2);
							a2.setAlpha(0.0);
							//a2.setInitialThreshold(0.0);
							a2.setWarmup(0);
							
							for (ModuleTypeEnum e1 : ModuleTypeEnum.values())
							{
								ModuleType module1 = of.createModuleType();
								ModuleType module2 = of.createModuleType();
								module1.setName(e1);
								
								for (ModuleTypeEnum e2 : ModuleTypeEnum.values())
								{
									
									module2.setName(e2);
									Modules modules1 = of.createSearchStrategyTypeModules();
									modules1.getModule().add(module1);
									Modules modules2 = of.createSearchStrategyTypeModules();
									modules2.getModule().add(module2);
									searchStrategy1.setModules(modules1);
									searchStrategy2.setModules(modules2);
									
									if (e1.equals(ModuleTypeEnum.RUIN_AND_RECREATE) &&
											e2.equals(ModuleTypeEnum.RUIN_AND_RECREATE))
									{
										RuinAndRecreateGroupType rr1 = of.createRuinAndRecreateGroupType();
										RuinAndRecreateGroupType rr2 = of.createRuinAndRecreateGroupType();
										module1.setRuinAndRecreateGroup(rr1);
										module2.setRuinAndRecreateGroup(rr2);
										
										for (RuinTypeEnum ruins1 : RuinTypeEnum.values())
										{
											RuinType ruin1 = of.createRuinType();
											ruin1.setName(ruins1);
											ruin1.setShare(0.5);
											rr1.setRuin(ruin1);
											for (RuinTypeEnum ruins2 : RuinTypeEnum.values())
											{
												RuinType ruin2 = of.createRuinType();
												ruin2.setName(ruins2);
												ruin2.setShare(0.5);
												rr2.setRuin(ruin2);
												
												for (InsertionTypeEnum ins1 : InsertionTypeEnum.values())
												{
													InsertionType i1 = of.createInsertionType();
													i1.setName(ins1);
													rr1.setInsertion(i1);
													for (InsertionTypeEnum ins2 : InsertionTypeEnum.values())
													{
														InsertionType i2 = of.createInsertionType();
														i2.setName(ins2);
														rr2.setInsertion(i2);
														
														if (ins1.equals(InsertionTypeEnum.BEST_INSERTION) &&
																ins2.equals(InsertionTypeEnum.BEST_INSERTION)) 
														// REGRET_INSERTION IS REALLY SLOW
														{
															//parsing xml config file
															String description = "**Algorithm Description**\n";
															description += "Selector 1: " + s1.getName() + " ";
															description += "Acceptor 1: " + a1.getName() + " ";
															description += "Probability 1: " + probability+ " ";
															description += "Module 1: " + module1.getName() + " ";
															description += "Ruin 1: " + ruin1.getName() + " ";
															description += "Insertion 1: " + i1.getName() + "\n";
															description += "Selector 2: " + s2.getName() + " ";
															description += "Acceptor 2: " + a2.getName() + " ";
															description += "Probability 2: " + (1-probability)+ " ";
															description += "Module 2: " + module2.getName() + " ";
															description += "Ruin 2: " + ruin2.getName() + " ";
															description += "Insertion 2: " + i2.getName();
															
															JAXBContext context = JAXBContext.newInstance(Algorithm.class);
															Marshaller marshaller = context.createMarshaller();
															marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
															marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.w3schools.com NewXMLSchema.xsd");
															marshaller.marshal(alg, new File("input/algorithmConfig.xml"));
															marshaller.marshal(alg, System.out);
															getSolution(description,args);
														}
														
														
													}
												}
											}
										}
									}
								}
							}
						}
						it1.remove();
					}
					
				}
			}
		}
	}
	
	public static void tryOneConfiguration(String[] args) throws JAXBException, SAXException
	{
		ObjectFactory of = new ObjectFactory();
		
		//create algorithm
		Algorithm alg = of.createAlgorithm();
		alg.setIterations(BigInteger.valueOf(10240));
		InsertionTypeEnum insTypeEnum = InsertionTypeEnum.BEST_INSERTION;
		InsertionType insertion = of.createInsertionType();
		insertion.setName(insTypeEnum);
		ConsiderFixedCosts consFixed = of.createInsertionTypeConsiderFixedCosts();
		consFixed.setValue("true");
		consFixed.setWeight(1.0);
		Construction construction = of.createAlgorithmConstruction();
		construction.setInsertion(insertion);
		insertion.setConsiderFixedCosts(consFixed);
		alg.setConstruction(construction);
		
		//build strategy
		Strategy strategy = of.createAlgorithmStrategy();
		strategy.setMemory(BigInteger.valueOf(3)); //memory setting
		alg.setStrategy(strategy);
		SearchStrategies searchStrategies = of.createAlgorithmStrategySearchStrategies();
		strategy.setSearchStrategies(searchStrategies);
		
		//try the search strategy with 2 search strategies
		SearchStrategyType searchStrategy1 = of.createSearchStrategyType();
		searchStrategy1.setName("strategy_1");
		searchStrategies.getSearchStrategy().add(searchStrategy1);
		SearchStrategyType searchStrategy2 = of.createSearchStrategyType();
		searchStrategy2.setName("strategy_2");
		searchStrategies.getSearchStrategy().add(searchStrategy2);
		SearchStrategyType searchStrategy3 = of.createSearchStrategyType();
		searchStrategy3.setName("strategy_3");
		searchStrategies.getSearchStrategy().add(searchStrategy3);
		
		SelectorType st1 = of.createSelectorType();
		st1.setName(SelectorTypeEnum.SELECT_BEST);
		
		SelectorType st2 = of.createSelectorType();
		st2.setName(SelectorTypeEnum.SELECT_BEST);
		
		SelectorType st3 = of.createSelectorType();
		st3.setName(SelectorTypeEnum.SELECT_RANDOMLY);
		
		searchStrategy1.setSelector(st1);
		searchStrategy2.setSelector(st2);
		searchStrategy3.setSelector(st3);
		double probability = 0.4;
		searchStrategy1.setProbability(probability);
		searchStrategy2.setProbability(0.4);
		searchStrategy3.setProbability(0.2);
		
		AcceptorType a1 = of.createAcceptorType();
		a1.setName(AcceptorTypeEnum.ACCEPT_NEW_REMOVE_WORST);
		AcceptorType a2 = of.createAcceptorType();
		a2.setName(AcceptorTypeEnum.GREEDY_ACCEPTANCE_MIN_VEH_FIRST);
		AcceptorType a3 = of.createAcceptorType();
		a3.setName(AcceptorTypeEnum.ACCEPT_NEW_REMOVE_FIRST);
		
		//setting acceptors
		searchStrategy1.setAcceptor(a1);
		searchStrategy2.setAcceptor(a2);
		searchStrategy3.setAcceptor(a3);
		a1.setAlpha(0.5);
		a1.setWarmup(0);
		a2.setAlpha(0.5);
		a2.setWarmup(0);
		a2.setAlpha(0.5);
		a3.setWarmup(0);
		
		//setting module
		ModuleType module1 = of.createModuleType();
		ModuleType module2 = of.createModuleType();
		ModuleType module3 = of.createModuleType();
		module1.setName(ModuleTypeEnum.RUIN_AND_RECREATE);
		module2.setName(ModuleTypeEnum.RUIN_AND_RECREATE);
		module3.setName(ModuleTypeEnum.RUIN_AND_RECREATE);
		Modules modules1 = of.createSearchStrategyTypeModules();
		modules1.getModule().add(module1);
		Modules modules2 = of.createSearchStrategyTypeModules();
		modules2.getModule().add(module2);
		Modules modules3 = of.createSearchStrategyTypeModules();
		modules3.getModule().add(module3);
		searchStrategy1.setModules(modules1);
		searchStrategy2.setModules(modules2);
		searchStrategy3.setModules(modules3);
		RuinAndRecreateGroupType rrg1 = of.createRuinAndRecreateGroupType();
		module1.setRuinAndRecreateGroup(rrg1);
		RuinAndRecreateGroupType rrg2 = of.createRuinAndRecreateGroupType();
		module2.setRuinAndRecreateGroup(rrg2);
		RuinAndRecreateGroupType rrg3 = of.createRuinAndRecreateGroupType();
		module3.setRuinAndRecreateGroup(rrg3);
		
		//setting ruin module
		RuinType ruin1 = of.createRuinType();
		ruin1.setName(RuinTypeEnum.RANDOM_RUIN);
		ruin1.setShare(0.5);
		rrg1.setRuin(ruin1);
		RuinType ruin2 = of.createRuinType();
		ruin2.setName(RuinTypeEnum.RADIAL_RUIN);
		ruin2.setShare(0.5);
		rrg2.setRuin(ruin2);
		RuinType ruin3 = of.createRuinType();
		ruin3.setName(RuinTypeEnum.RANDOM_RUIN);
		ruin3.setShare(0.5);
		rrg3.setRuin(ruin3);
		
		//setting insertion module
		InsertionType insertion1 = of.createInsertionType();
		insertion1.setName(InsertionTypeEnum.BEST_INSERTION);
		rrg1.setInsertion(insertion1);
		InsertionType insertion2 = of.createInsertionType();
		insertion2.setName(InsertionTypeEnum.BEST_INSERTION);
		rrg2.setInsertion(insertion2);
		InsertionType insertion3 = of.createInsertionType();
		insertion3.setName(InsertionTypeEnum.REGRET_INSERTION);
		rrg3.setInsertion(insertion3);
		
		//create description of the algorithm 
		String description = getDescription(alg);
		
		//parsing Java class into XML file: input/algorithmConfig.xml
		JAXBContext context = JAXBContext.newInstance(Algorithm.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.w3schools.com NewXMLSchema.xsd");
		marshaller.marshal(alg, new File("input/algorithmConfig.xml"));
		marshaller.marshal(alg, System.out); //print xml on the screen
		
		//try the created configuration
		getSolution(description,args);
		
	}
	
	public static void tryMoreStrategies(String[] args)
	{
		ObjectFactory of = new ObjectFactory();
		
		//create algorithm
		Algorithm alg = of.createAlgorithm();
		alg.setIterations(BigInteger.valueOf(1024));
		InsertionTypeEnum insTypeEnum = InsertionTypeEnum.BEST_INSERTION;
		InsertionType insertion = of.createInsertionType();
		insertion.setName(insTypeEnum);
		ConsiderFixedCosts consFixed = of.createInsertionTypeConsiderFixedCosts();
		consFixed.setValue("true");
		consFixed.setWeight(1.0);
		Construction construction = of.createAlgorithmConstruction();
		construction.setInsertion(insertion);
		insertion.setConsiderFixedCosts(consFixed);
		alg.setConstruction(construction);
		
		//build strategy
		Strategy strategy = of.createAlgorithmStrategy();
		strategy.setMemory(BigInteger.valueOf(3)); //memory setting
		alg.setStrategy(strategy);
		SearchStrategies searchStrategies = of.createAlgorithmStrategySearchStrategies();
		strategy.setSearchStrategies(searchStrategies);
		
		SearchStrategyType searchStrategy1 = of.createSearchStrategyType();
		searchStrategy1.setName("strategy_1");
		searchStrategies.getSearchStrategy().add(searchStrategy1);
		SearchStrategyType searchStrategy2 = of.createSearchStrategyType();
		searchStrategy2.setName("strategy_2");
		searchStrategies.getSearchStrategy().add(searchStrategy2);
		SearchStrategyType searchStrategy3 = of.createSearchStrategyType();
		searchStrategy3.setName("strategy_3");
		searchStrategies.getSearchStrategy().add(searchStrategy3);
		SearchStrategyType searchStrategy4 = of.createSearchStrategyType();
		searchStrategy4.setName("strategy_4");
		searchStrategies.getSearchStrategy().add(searchStrategy4);
		SearchStrategyType searchStrategy5 = of.createSearchStrategyType();
		searchStrategy5.setName("strategy_5");
		searchStrategies.getSearchStrategy().add(searchStrategy5);
		SearchStrategyType searchStrategy6 = of.createSearchStrategyType();
		searchStrategy6.setName("strategy_6");
		searchStrategies.getSearchStrategy().add(searchStrategy6);
		
		for (SelectorTypeEnum se : SelectorTypeEnum.values())
		{
			for (SearchStrategyType st : searchStrategies.getSearchStrategy())
			{
				SelectorType st1 = of.createSelectorType();
				st1.setName(se);
				st.setSelector(st1);
				st.setProbability(0.16);
				
				for (AcceptorTypeEnum ae : AcceptorTypeEnum.values())
				{
					AcceptorType a1 = of.createAcceptorType();
					a1.setName(ae);
					st.setAcceptor(a1);
					a1.setAlpha(0.0);
					a1.setWarmup(0);
					
					ModuleType module1 = of.createModuleType();
					module1.setName(ModuleTypeEnum.RUIN_AND_RECREATE);
					Modules modules1 = of.createSearchStrategyTypeModules();
					modules1.getModule().add(module1);
					st.setModules(modules1);
					RuinAndRecreateGroupType rrg1 = of.createRuinAndRecreateGroupType();
					module1.setRuinAndRecreateGroup(rrg1);
					
					for (RuinTypeEnum re : RuinTypeEnum.values())
					{
						RuinType ruin1 = of.createRuinType();
						ruin1.setName(re);
						for (int i = 0; i < 10; i+=2) {
							double share = ((double)i)/10.0;
							ruin1.setShare(share);
							
						}
					}
				}
			}
		}
		
	}

	public static String getDescription(Algorithm alg) {
		String description = "**Algorithm Description**\n";
		description += "Construction: " + alg.getConstruction().getInsertion().getName() + " ";
		description += "Iterations: " + alg.getIterations() + " ";
		description += "Memory: " + alg.getStrategy().getMemory() + "\n";
		
		int i = 1;
		for (SearchStrategyType st : alg.getStrategy().getSearchStrategies().getSearchStrategy())
		{
			description += "Selector " + i + ": " + st.getSelector().getName() + " ";
			description += "Acceptor " + i + ": " + st.getAcceptor().getName() + " ";
			description += "Probability " + i + ": " + st.getProbability() + " ";
			description += "Module " + i + ": " + st.getModules().getModule().get(0).getName() + " ";
			description += "Ruin " + i + ": " + st.getModules().getModule().get(0).getRuinAndRecreateGroup().getRuin().getName() + " ";
			description += "Insertion " + i + ": " + st.getModules().getModule().get(0).getRuinAndRecreateGroup().getInsertion().getName() + "\n";
			i++;
		}
		return description;
	}

	private static void getSolution(String description, String[] args) {
		
		Examples.createOutputFolder();
		
		// Read input parameters
		OROoptions options = new OROoptions(args);
		OROutils.writeDescription(description, (String)options.get(CONSTANTS.OUTPUT));
		
		for(int r=0; r<(int)options.get(CONSTANTS.REPETITION); r++) {
			// Time tracking
			long startTime = System.currentTimeMillis();
			// Create a vrp problem builder
			VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
			// A solomonReader reads solomon-instance files, and stores the required information in the builder.
			new SolomonReader(vrpBuilder).read("input/" + options.get(PARAMS.INSTANCE));
			VehicleRoutingProblem vrp = vrpBuilder.build();
			// Create the instace and solve the problem
			VehicleRoutingAlgorithm vra = VehicleRoutingAlgorithms.readAndCreateAlgorithm(vrp, 
					(int)options.get(CONSTANTS.THREADS), (String)options.get(CONSTANTS.CONFIG));
			setTimeLimit(vra, (long)options.get(CONSTANTS.TIME));
			
			// Solve the problem
			Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
			// Extract the best solution
			VehicleRoutingProblemSolution solution = new SelectBest().selectSolution(solutions);
									
			// Print solution on a file
			OROutils.write(solution, (String)options.get(PARAMS.INSTANCE), System.currentTimeMillis()-startTime, (String)options.get(CONSTANTS.OUTPUT));
		}
	}
}
