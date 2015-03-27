package org.apache.maven.plugins.shade.resource;

import org.apache.maven.plugins.shade.relocation.Relocator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.jar.JarOutputStream;

/**
 * Created by scottflo on 3/26/15.
 */
public class RootResourceRelocatorResourceTransformer implements ResourceTransformer
{
	@Override
	public boolean canTransformResource(String resource)
	{
		return !resource.contains("/");
	}

	@Override
	public void processResource(String resource, InputStream is, List<Relocator> relocators) throws IOException
	{

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
