# ToolGeodesic

### ¿Que es esto?

Es una herramienta escrita en java que provee la capacidad de transformar coordenadas geograficas (Latitud y Longitud) hacia coordenadas UTM (Este, Norte, Huso y Hemisferio). Destinado a estudiantes de Ingeníeria Civil y Aerofotogrametría como un apoyo a la educación.

*Nota: el datum predeterminado es WGS84.*

## Descargas
* Descargar el Jar (Ejecutable):
	[Download](https://github.com/mdt496/ToolGeodesic/archive/master.zip)
* Descargar el proyecto:
	[Github download](https://github.com/mdt496/ToolGeodesic/archive/master.zip)

Véase el [tutorial](https://www.youtube.com/watch?v=ellKDxx_Ic4&feature=youtu.be "ToolGeodesic") para más información del uso.

## Ejemplo de uso
*En plataformas Windows*
* Abre la consola Win+R cmd
* Verifica que este instalado java `java -version`
* Cambia de directorio donde pusiste el jar `cd /d C:\Users\%username%\Desktop`
* Ahora puedes hacer uso de los comandos.

```python
java -jar ToolGeodesic.jar GeoToUtm -16.32222 -68.222252
```

Resultados:
```bash
E:=    583079.8940886903
N:=    8195263.499656447
Hem:=  S
Huso:= 19

DatumInfo:
a (semieje mayor)       6378137.0
b (semieje menor)       6356752.314
Excentricidad           0.08181919131086947
2ª Excentric. (e')      0.08209443842268527
e' ²                    0.006739496819936063
c (radio polar de curvatura)    6399593.626005325
```
* Para pedir ayuda rapida en consola ingresa lo siguiente
```python
java -jar ToolGeodesic.jar
```
Esto producira:

```bash
Modo de uso:

UtmToGeo Huso Hemisferio Este Norte
UtmToGeo 20 S 190676.7 8928670
UtmToGeo Huso Hemisferio Este Norte aDatum bDatum
UtmToGeo 20 S 190676.7 8928670 6378137. 6356752.314

GeoToUtm Lat Lon
GeoToUtm -9.680157 -65.818614
GeoToUtm Lat Lon aDatum bDatum
GeoToUtm -9.680157 -65.818614 6378137. 6356752.314
```

#### License

Creative Commons BY-NC-ND.

> Copyright © 2019 MDT496 ESTUDIOS
	Marco Mendieta Parihuancollo
	[github.com/mdt496](https://github.com/mdt496)