import TinyMidiPCM from './tinymidipcm/index.js';

(async () => {
    let renderEndSeconds = 0;
    let playingMIDIBuffer = undefined;
    let playingMIDIVolume = 1;

    const pcmPlayerOptions = {
        inputCodec: 'Float32',
        channels: 2,
        sampleRate: 44100,
        onended: () => {
            const timeSeconds = Math.floor(player.audioCtx.currentTime);
            if (renderEndSeconds > 0 && Math.abs(timeSeconds - renderEndSeconds) <= 2) {
                renderEndSeconds = 0;

                if (playingMIDIBuffer) {
                    window._tinyMidiPlay(playingMIDIBuffer, playingMIDIVolume);
                }
            }
        },
        flushTime: 1000
    };

    const player = new PCMPlayer(pcmPlayerOptions);

    const tinyMidiPCM = new TinyMidiPCM({
        renderInterval: 100,
        onPCMData: (pcm) => {
            if (player.audioCtx) {
                player.feed(pcm);
            }
        },
        onRenderEnd: (ms) => {
            renderEndSeconds = Math.floor(ms / 1000);
        },
        bufferSize: 1024 * 100
    });

    await tinyMidiPCM.init();

    const soundfontRes = await fetch('/SCC1_Florestan.sf2');
    const soundfontBuffer = new Uint8Array(
        await soundfontRes.arrayBuffer()
    );

    tinyMidiPCM.setSoundfont(soundfontBuffer);

    window._tinyMidiStop = async () => {
        if (player.audioCtx) {
            await player.pause();
            player.destroy();
        }

        playingMIDIBuffer = undefined;
    };

    window._tinyMidiPlay = async (midiBuffer, vol = 1) => {
        await window._tinyMidiStop();

        player.init(pcmPlayerOptions);
        playingMIDIVolume = vol;
        player.volume(vol);

        playingMIDIBuffer = midiBuffer;
        tinyMidiPCM.render(midiBuffer);
    };

    window._tinyMidiVolume = (vol = 1) => {
        playingMIDIVolume = vol;
        player.volume(vol);
    };
})();
