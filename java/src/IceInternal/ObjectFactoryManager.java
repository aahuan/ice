// **********************************************************************
//
// Copyright (c) 2001
// MutableRealms, Inc.
// Huntsville, AL, USA
//
// All Rights Reserved
//
// **********************************************************************

package IceInternal;

public final class ObjectFactoryManager
{
    public synchronized void
    add(Ice.ObjectFactory factory, String id)
    {
        _factoryMap.put(id, factory);
    }

    public synchronized void
    remove(String id)
    {
        _factoryMap.remove(id);
    }

    public synchronized Ice.ObjectFactory
    find(String id)
    {
        return (Ice.ObjectFactory)_factoryMap.get(id);
    }

    //
    // Only for use by Instance
    //
    ObjectFactoryManager()
    {
    }

    void
    destroy()
    {
        java.util.Iterator i = _factoryMap.values().iterator();
        while(i.hasNext())
        {
            Ice.ObjectFactory factory = (Ice.ObjectFactory)i.next();
            factory.destroy();
        }
        _factoryMap.clear();
    }

    private java.util.HashMap _factoryMap = new java.util.HashMap();
}
