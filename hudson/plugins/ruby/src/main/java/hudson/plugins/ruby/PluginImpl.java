package hudson.plugins.ruby;

import hudson.Plugin;
import hudson.tasks.BuildStep;

/**
 * Entry point for Ruby plugin
 *
 * @author Vivek Pandey
 */
public class PluginImpl extends Plugin {
    public void start() throws Exception {
        BuildStep.BUILDERS.add(Ruby.DESCRIPTOR);
    }
}
