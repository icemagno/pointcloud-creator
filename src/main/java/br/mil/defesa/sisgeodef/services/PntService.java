package br.mil.defesa.sisgeodef.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import de.tum.gis.tiles3d.database.sqlite.SqliteDBManagerFactory;
import de.tum.gis.tiles3d.generator.PntcConfig;
import de.tum.gis.tiles3d.generator.PntcGenerationException;
import de.tum.gis.tiles3d.generator.PntcGenerator;
import de.tum.gis.tiles3d.model.Refine;
import de.tum.gis.tiles3d.util.Logger;

@Service
public class PntService {

	
	@PostConstruct
	public void doGen() {
		readSourcePointData();
	}	
	

	private void readSourcePointData() {
		
		PntcConfig config = new PntcConfig();		
		config.setInputPath( "/pointsource" ) ;
		config.setSrid( "2994" );
		config.setTargetSrid( "4326" );
		config.setMustReproject( true );
		config.setSeparatorCharacter( " " );
		config.setColorBitSize( 16 );
		config.setZScaleFactor( 1 );
		config.setTileSize( 100 );
		config.setMaxNumOfPointsPerTile( 1000 );
		config.setOutputFolderPath( "/pointdata" );
		config.setRefinamentModel( Refine.ADD );
		config.setzOffset( 4000 );
		
		
		SqliteDBManagerFactory dbManagerFactory = new SqliteDBManagerFactory(config);
		final PntcGenerator generator = new PntcGenerator(config, dbManagerFactory);	
		
		boolean success = false;
		try {
			success = generator.doProcess();
		} catch (PntcGenerationException e) {
			Logger.error(e.getMessage());
			Throwable cause = e.getCause();
			while (cause != null) {
				Logger.error("Cause: " + cause.getMessage());
				cause = cause.getCause();
			}
		}	

		
		if (success) {
			Logger.info("Export successfully finished.");
		} else {
			Logger.warn("Export aborted.");
		}		
	}
	
	
}
