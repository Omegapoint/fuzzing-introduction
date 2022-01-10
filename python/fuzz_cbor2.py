import atheris

with atheris.instrument_imports():
    import cbor2
    import sys


@atheris.instrument_func
def TestOneInput(data):
    try:
        parsed = cbor2.loads(data)
        # Comment out print to get more execution/s
        print(parsed)
    except cbor2.CBORDecodeError:
        # What more exceptions can we find?
        pass

atheris.Setup(sys.argv, TestOneInput)
atheris.Fuzz()