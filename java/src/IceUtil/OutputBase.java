// **********************************************************************
//
// Copyright (c) 2002
// MutableRealms, Inc.
// Huntsville, AL, USA
//
// All Rights Reserved
//
// **********************************************************************

package IceUtil;

public class OutputBase
{
    public
    OutputBase()
    {
        _out = null;
        _pos = 0;
        _indent = 0;
        _separator = true;
        _useTab = true;
        _indentSize = 4;
    }

    public
    OutputBase(java.io.PrintWriter out)
    {
        _out = out;
        _pos = 0;
        _indent = 0;
        _separator = true;
        _useTab = true;
        _indentSize = 4;
    }

    public
    OutputBase(String s)
    {
        _out = null;
        _pos = 0;
        _indent = 0;
        _separator = true;
        _useTab = true;
        _indentSize = 4;

        open(s);
    }

    public void
    open(String s)
    {
        try
        {
            java.io.FileWriter fw = new java.io.FileWriter(s);
            java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
            _out = new java.io.PrintWriter(bw);
        }
        catch (java.io.IOException ex)
        {
        }
    }

    public void
    print(String s)
    {
        final char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] == '\n')
            {
                _pos = 0;
            }
            else
            {
                ++_pos;
            }
        }

        _out.print(s);
    }

    public void
    inc()
    {
        _indent += _indentSize;
    }

    public void
    dec()
    {
        assert(_indent >= _indentSize);
        _indent -= _indentSize;
    }

    public void
    useCurrentPosAsIndent()
    {
        _indentSave.addFirst(new Integer(_indent));
        _indent = _pos;
    }

    public void
    zeroIndent()
    {
        _indentSave.addFirst(new Integer(_indent));
        _indent = 0;
    }

    public void
    restoreIndent()
    {
        assert(!_indentSave.isEmpty());
        _indent = ((Integer)_indentSave.removeFirst()).intValue();
    }

    public void
    setIndent(int indentSize)
    {
        _indentSize = indentSize;
    }

    public void
    setUseTab(boolean useTab)
    {
        _useTab = useTab;
    }

    public void
    nl()
    {
        _out.println();
        _pos = 0;
        _separator = true;

        int indent = _indent;

        if(_useTab)
        {
            while(indent >= 8)
            {
                indent -= 8;
                _out.print('\t');
                _pos += 8;
            }
        }
        else
        {
            while(indent >= _indentSize)
            {
                indent -= _indentSize;
                _out.print("    ");
                _pos += _indentSize;
            }
        }

        while(indent > 0)
        {
            --indent;
            _out.print(' ');
            ++_pos;
        }

        _out.flush();
    }

    public void
    sp()
    {
        if(_separator)
        {
            _out.println();
        }
    }

    public boolean
    valid()
    {
        return (_out != null);
    }

    protected java.io.PrintWriter _out;
    protected int _pos;
    protected int _indent;
    protected java.util.LinkedList _indentSave = new java.util.LinkedList();
    protected boolean _separator;

    protected boolean _useTab;
    protected int _indentSize;
}
