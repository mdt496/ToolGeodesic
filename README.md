# ToolGeodesic
> © 2019 MDT496 ESTUDIOS
	Marco Mendieta Parihuancollo
	[github.com/mdt496](https://github.com/mdt496)

### ¿Que es esto?

Es una herramienta escrita en java que provee la capacidad de transformar coordenadas geograficas (Latitud y Longitud) hacia coordenadas UTM (Este, Norte, Huso y Hemisferio). Destinado a estudiantes de Ingeníeria Civil y Aerofotogrametría como un apoyo a la educación. 

*Nota: el datum predeterminado es WGS84.*

## Instalacion
* Descargar el repositorio de GitHub
* Abrir con un editor Java (NetBeans ó Eclipse)

Véase el [tutorial](https://www.youtube.com/watch?v=ellKDxx_Ic4&feature=youtu.be "ToolGeodesic") para más información del uso.

## Ejemplo
```python
	System.out.println("Demostración: Transformación coord. UTM a GEO");
	UtmToGeo utg = new UtmToGeo(20,"S",190676.7544318299,8928670.196644317);
	System.out.println(utg.getLatitude());
	System.out.println(utg.getLongitude());
	
	System.out.println("\nDemostración: Transformación coord. GEO a UTM");
	GeoToUtm gtu = new GeoToUtm(-9.680155555555555,-65.81861388888889);
	System.out.println(gtu.getEste());
	System.out.println(gtu.getNorte());
	System.out.println(gtu.getHemisferio());
	System.out.println(gtu.getHuso());
``` 

Resultados:

	Demostración: Transformación coord. UTM a GEO
	-9.680155555688025
	-65.81861388113803

	Demostración: Transformación coord. GEO a UTM
	190676.7544318299
	8928670.196644317
	S
	20
