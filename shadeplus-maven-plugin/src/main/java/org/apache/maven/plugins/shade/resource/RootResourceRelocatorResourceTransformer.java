package org.apache.maven.plugins.shade.resource;

import org.apache.maven.plugins.shade.relocation.Relocator;
import org.apache.maven.plugins.shade.util.Pair;
import org.codehaus.plexus.util.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

/**
 * Created by scottflo on 3/26/15.
 */
public class RootResourceRelocatorResourceTransformer implements ResourceTransformer
{
	private Map<Pair<String, String>, InputStream> resources = new HashMap<Pair<String, String>, InputStream>();

	private boolean transformed = true;
	private static final String ROOT_DIR_NAME = "ROOT_RESOURCES";

	@Override
	public boolean canTransformResource(String resource)
	{
		return !resource.contains("/");
	}

	@Override
	public void processResource(String resource, InputStream is, List<Relocator> relocators) throws IOException
	{
		//No-op
	}

	public void processResource(String jarName, String resource, InputStream is)
	{
		resources.put(new Pair(jarName,resource), is);
		transformed = true;
	}


	@Override
	public boolean hasTransformedResource()
	{
		return transformed;
	}

	@Override
	public void modifyOutputStream(JarOutputStream os) throws IOException
	{
		for(Pair<String, String> rootResource : resources.keySet())
		{
			InputStream in = resources.get(rootResource);
			String relocatedResource = ROOT_DIR_NAME + "/" + rootResource.getKey() + "/" + rootResource.getValue();
			os.putNextEntry( new JarEntry( relocatedResource ) );
			IOUtil.copy(in, os);
			in.close();
		}
		resources.clear();
		transformed = false;
	}
}
