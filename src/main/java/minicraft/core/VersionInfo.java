package minicraft.core;

import com.google.gson.JsonObject;

import minicraft.saveload.Version;
import minicraft.util.JsonUtil;

public class VersionInfo {
	public final Version version;
	public final String releaseUrl;
	public final String releaseName;

	public VersionInfo(JsonObject releaseInfo) {
		String versionTag = JsonUtil.getString(releaseInfo, "tag_name").substring(1); // Cut off the "v" at the beginning
		version = new Version(versionTag);

		releaseUrl = JsonUtil.getString(releaseInfo, "html_url");

		releaseName = JsonUtil.getString(releaseInfo, "name");
	}

	public VersionInfo(Version version, String releaseUrl, String releaseName) {
		this.version = version;
		this.releaseUrl = releaseUrl;
		this.releaseName = releaseName;
	}
}
