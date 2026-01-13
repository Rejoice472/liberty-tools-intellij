package io.openliberty.tools.intellij.lsp4jakarta.lsp4ij.pojo;

import org.eclipse.lsp4mp.commons.MicroProfileJavaDiagnosticsSettings;

import java.util.List;

public class JakartaDiagnosticsSettings extends MicroProfileJavaDiagnosticsSettings {

    public JakartaDiagnosticsSettings(List<String> patterns, int jakartaVersion) {
        super(patterns);
        this.jakartaVersion = jakartaVersion;
    }
private final int jakartaVersion;

    public int getJakartaVersion() {
        return jakartaVersion;
    }
}
