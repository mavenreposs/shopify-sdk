package com.shopify.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopify.model.structs.Metafield;
import com.shopify.model.enums.MetafieldValueType;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class ImageAltTextCreationRequest {

	public static final String KEY = "alt";
	public static final String NAMESPACE = "tags";
	public static final MetafieldValueType VALUE_TYPE = MetafieldValueType.STRING;

	public static interface ImageAltTextStep {
		public BuildStep withImageAltText(final String imageAltText);
	}

	public static interface BuildStep {
		public List<Metafield> build();
	}

	public static ImageAltTextStep newBuilder() {
		return new Steps();
	}

	private static class Steps implements ImageAltTextStep, BuildStep {

		private Metafield imageAltTextMetafield;

		@Override
		public List<Metafield> build() {
			return Arrays.asList(imageAltTextMetafield);
		}

		@Override
		public BuildStep withImageAltText(String imageAltText) {
			this.imageAltTextMetafield = new Metafield();
			imageAltTextMetafield.setKey(KEY);
			imageAltTextMetafield.setValue(imageAltText);
			imageAltTextMetafield.setNamespace(NAMESPACE);
			imageAltTextMetafield.setValueType(VALUE_TYPE);
			return this;
		}

	}

}
