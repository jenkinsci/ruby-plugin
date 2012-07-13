package hudson.plugins.ruby;

import hudson.Extension;
import hudson.FilePath;
import hudson.model.AbstractProject;
import hudson.model.Descriptor;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.tasks.CommandInterpreter;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Invokes the ruby interpreter and invokes the Ruby script entered on the hudson build configuration.
 * <p/>
 * It is expected that the ruby interpreter is available on the system PATH.
 *
 * @author Vivek Pandey
 */
public class Ruby extends CommandInterpreter {

    @DataBoundConstructor
    public Ruby(String command) {
        super(command);
    }

    public String[] buildCommandLine(FilePath script) {
        return new String[]{"ruby", "-v", script.getRemote()};
    }

    protected String getContents() {
        return command;
    }

    protected String getFileExtension() {
        return ".rb";
    }

    @Override
    public Descriptor<Builder> getDescriptor() {
        return DESCRIPTOR;
    }

    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        DescriptorImpl() {
            super(Ruby.class);
            load();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public Builder newInstance(StaplerRequest req, JSONObject formData) {
            return new Ruby(formData.getString("ruby"));
        }

        public String getDisplayName() {
            return "Execute Ruby script";
        }

        @Override
        public String getHelpFile() {
            return "/plugin/ruby/help.html";
        }
    }
}
