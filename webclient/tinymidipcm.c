#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

#define TSF_IMPLEMENTATION
#include "tsf.h"

#define TML_IMPLEMENTATION
#include "tml.h"

// TODO customisable
#define SAMPLE_RATE 22050

#define TEN_MEGABYTES (10 * 1024 * 1024)

uint8_t* tinymidipcm_render_pcm(uint8_t *soundfont_buffer,
                           int soundfont_length,
                           uint8_t *midi_buffer,
                           int midi_length,
                           int *pcm_length) {
    tsf *soundfont = tsf_load_memory(soundfont_buffer, soundfont_length);
    tsf_set_output(soundfont, TSF_STEREO_INTERLEAVED, 22050, 0.0f);
    tml_message *midi_message = tml_load_memory(midi_buffer, midi_length);

    int allocated_length = TEN_MEGABYTES;
    uint8_t *rendered_bytes = malloc(allocated_length);

    int bytes_written = 0;

    if (rendered_bytes == NULL) {
        fprintf(stderr, "unable to allocate %d bytes for render\n",
                allocated_length);

        return NULL;
    }

    int block_size = TSF_RENDER_EFFECTSAMPLEBLOCK;
    double msecs = 0;

    do {
        msecs += block_size * (1000.0 / SAMPLE_RATE);

        while (midi_message && msecs >= midi_message->time) {
            switch (midi_message->type) {
            case TML_PROGRAM_CHANGE:
                tsf_channel_set_presetnumber(soundfont, midi_message->channel,
                                             midi_message->program,
                                             (midi_message->channel == 9));
                break;
            case TML_NOTE_ON:
                tsf_channel_note_on(soundfont, midi_message->channel,
                                    midi_message->key,
                                    midi_message->velocity / 127.0f);
                break;
            case TML_NOTE_OFF:
                tsf_channel_note_off(soundfont, midi_message->channel,
                                     midi_message->key);
                break;
            case TML_PITCH_BEND:
                tsf_channel_set_pitchwheel(soundfont, midi_message->channel,
                                           midi_message->pitch_bend);
                break;
            case TML_CONTROL_CHANGE:
                tsf_channel_midi_control(soundfont, midi_message->channel,
                                         midi_message->control,
                                         midi_message->control_value);
                break;
            }

            midi_message = midi_message->next;
        }

        if (bytes_written + (block_size * 4) >=
            allocated_length) {
            allocated_length += TEN_MEGABYTES;

            rendered_bytes =
                realloc(rendered_bytes, allocated_length);

            if (rendered_bytes == NULL) {
                fprintf(stderr, "unable to allocate %d bytes for render\n",
                        allocated_length);

                return NULL;
            }
        }

        if (!midi_message) {
            *pcm_length = bytes_written;

            return rendered_bytes;
        }

        tsf_render_short(
            soundfont,
            (int16_t *)(rendered_bytes + bytes_written),
            block_size, 0);

        bytes_written += block_size * 4;
    } while (1);

    return rendered_bytes;
}

int main(int argc, char **argv) {
    int soundfont_length = 3367466;
    FILE *soundfont_file = fopen("./gm.sf2", "r");
    uint8_t *soundfont_buffer = malloc(soundfont_length);
    fread(soundfont_buffer, soundfont_length, 1, soundfont_file);
    fclose(soundfont_file);

    int midi_length = 17704;
    FILE *midi_file = fopen("./runescape-flute salad.mid", "r");
    uint8_t *midi_buffer = malloc(midi_length);
    fread(midi_buffer, midi_length, 1, midi_file);
    fclose(midi_file);

    int pcm_length = 0;

    uint8_t *pcm = tinymidipcm_render_pcm(soundfont_buffer, soundfont_length,
                                          midi_buffer, midi_length, &pcm_length);

    if (pcm) {
        //printf("success: %d\n", pcm_length);
        FILE *pcm_file = fopen("./out.pcm", "w");
        fwrite(pcm, pcm_length, 1, pcm_file);
        fclose(pcm_file);
    }

    free(pcm);
}
