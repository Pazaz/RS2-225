import loadTinyMidiPCM from './tinymidipcm.mjs';

class TinyMidiPCM {
    constructor() {
        this.wasmModule = undefined;

        this.soundfontPtr = undefined;
        this.soundfontLength = 0;
    }

    async init() {
        if (this.wasmModule) {
            return;
        }

        // check if node
        // http://philiplassen.com/2021/08/11/node-es6-emscripten.html
        if (typeof process !== 'undefined') {
            const { dirname } = await import(/* webpackIgnore: true */ 'path');
            const { createRequire } = await import(/* webpackIgnore: true */ 'module');

            globalThis.__dirname = dirname(import.meta.url);
            globalThis.require = createRequire(import.meta.url);
        }

        this.wasmModule = await loadTinyMidiPCM();
    }

    ensureInitialized() {
        if (!this.wasmModule) {
            throw new Error(
                `${this.constructor.name} not initalized. call .init()`
            );
        }
    }

    setSoundfont(buffer) {
        const { _malloc, _free, HEAPU8 } = this.wasmModule;

        if (this.soundfontPtr) {
            _free(this.soundfontPtr);
        }

        this.soundfontLength = buffer.length;
        this.soundfontPtr = _malloc(this.soundfontLength);

        HEAPU8.set(buffer, this.soundfontPtr);
    }

    render(midiBuffer) {
        this.ensureInitialized();

        if (!this.soundfontPtr || !this.soundfontLength) {
            throw new Error('no soundfont buffer set. call .setSoundfont');
        }

        const {
            _malloc,
            _free,
            getValue,
            setValue,
            HEAPU8,
            _tinymidipcm_render_pcm
        } = this.wasmModule;

        const midiPtr = _malloc(midiBuffer.length);
        HEAPU8.set(midiBuffer, midiPtr);

        const pcmLengthPtr = _malloc(4);
        setValue(pcmLengthPtr, 123, 'i32');

        const pcmPtr = _tinymidipcm_render_pcm(
            this.soundfontPtr,
            this.soundfontLength,
            midiPtr,
            midiBuffer.length,
            pcmLengthPtr
        );

        const pcmLength = getValue(pcmLengthPtr, 'i32');

        const pcm = new Uint8Array(pcmLength);

        const view = this.wasmModule.HEAPU8.subarray(
            pcmPtr,
            pcmPtr + pcmLength
        );

        pcm.set(view);

        return pcm;
    }
}

export default TinyMidiPCM;
