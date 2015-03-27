package org.apache.maven.plugins.shade.resource;

import javafx.util.Pair;
import org.apache.maven.plugins.shade.relocation.Relocator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarOutputStream;

/**
 * Created by scottflo on 3/26/15.
 */
public class RootResourceRelocatorResourceTransformer implements ResourceTransformer
{
	private Map<Pair<String, String>, InputStream> resources = new HashMap<Pair<String, String>, InputStream>();

	@Override
	public boolean canTransformResource(String resource)
	{
		return !resource.contains("/");
	}

	@Override
	public void processResource(String resource, InputStream is, List<Relocator> relocators) throws IOException
	{
		throw new UnsupportedOperationException("Use other processResourceImplementation");
	}

	public void processResource(String jarName, String resource, InputStream is)
	{
		resources.put(new Pair(jarName,resource), is);
	}


	@Override
	public boolean hasTransformedResource()
	{
		return false;
	}

	@Override
	public void modifyOutputStream(JarOutputStream os) throws IOException
	{

	}
}
