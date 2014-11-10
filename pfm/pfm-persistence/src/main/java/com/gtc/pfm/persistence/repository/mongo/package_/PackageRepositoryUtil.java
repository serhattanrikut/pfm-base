/**
 * 
 */
package com.gtc.pfm.persistence.repository.mongo.package_;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;

import com.gtc.pfm.domain.Location;
import com.gtc.pfm.domain.Package;

/**
 * @author stanriku
 * 
 */
public class PackageRepositoryUtil {

	/**
	 * converts {@link GeoResults} list to {@link Package} list
	 * 
	 * @param geoPackages
	 * @return list of {@link Package}
	 */
	public static List<Package> convertGeoPackages(
			GeoResults<Package> geoPackages) {

		if (geoPackages == null || geoPackages.getContent() == null
				|| geoPackages.getContent().size() < 0) {
			return null;
		}

		List<GeoResult<Package>> geoContent = geoPackages.getContent();
		List<Package> packages = new ArrayList<Package>();

		for (GeoResult<Package> geoResult : geoContent) {
			Package content = geoResult.getContent();
			content.setDistance(geoResult.getDistance().getNormalizedValue());
			packages.add(content);
		}
		return packages;
	}

	/**
	 * converts {@link Location} to {@link Point}
	 * 
	 * @param location
	 * @return {@link Point}
	 */
	public static Point convertPoint(Location location) {

		if (location == null)
			return null;

		Point point = new Point(location.getLongitude(), location.getLatitude());
		return point;
	}

}
