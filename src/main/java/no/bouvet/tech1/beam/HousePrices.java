package no.bouvet.tech1.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.Max;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptors;

public class HousePrices {

    public interface HousePriceOptions extends PipelineOptions {

        @Description("Path of the file to read from")
        @Default.String("gs://bouvet-tech1-feb2017/pp-complete.csv")
        String getInputFile();

        void setInputFile(String value);

        @Description("Path of the file(s) to write to")
        @Default.String("gs://bouvet-tech1-feb2017/output/out")
        String getOutput();

        void setOutput(String value);
    }

    public static class ExtractPricesFn extends DoFn<String, KV<String, Integer>> {

        private static final String DOUBLEQUOTE = "\"";
        private static final String REPLACEMENT = "";
        private static final String COMMA = ",";

        @ProcessElement
        public void processElement(ProcessContext c) {
            String[] fields = c.element().replaceAll(DOUBLEQUOTE, REPLACEMENT).split(COMMA);
            Integer price = Integer.valueOf(fields[1]);
            String year = fields[2].substring(0, 4);
            c.output(KV.of(year, price));
        }

    }

    public static void main(String[] args) {
        HousePriceOptions options =
            PipelineOptionsFactory.fromArgs(args).withValidation().as(HousePriceOptions.class);

        Pipeline p = Pipeline.create(options);

        p.apply("Read input", TextIO.Read.from(options.getInputFile()))
            .apply("Extract price and year", ParDo.of(new ExtractPricesFn()))
            .apply("Group max per year", Max.integersPerKey())
            .apply(
                "Format results",
                MapElements.via((KV<String, Integer> input) ->  input.getKey() + ": " + input.getValue())
                    .withOutputType(TypeDescriptors.strings())
            )
            .apply("Write output", TextIO.Write.to(options.getOutput()));

        p.run().waitUntilFinish();
    }
}
