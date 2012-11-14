package fr.obsmip.sedoo.core.parameter.parameterprovider;

public interface WritableParameterProvider extends ReadableParameterProvider
{
	public void setParameter(String key,String value);
}
