/**
 * Copyright (c) 2008-2011 Sonatype, Inc.
 *
 * All rights reserved. Includes the third-party code listed at http://www.sonatype.com/products/nexus/attributions.
 * Sonatype and Sonatype Nexus are trademarks of Sonatype, Inc. Apache Maven is a trademark of the Apache Foundation.
 * M2Eclipse is a trademark of the Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.plugins.p2.repository.its.nxcm0670;

import java.io.File;

import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.sonatype.nexus.plugins.p2.repository.its.AbstractNexusProxyP2IT;
import org.sonatype.nexus.test.utils.TaskScheduleUtil;

public class NXCM0670UpdateSiteProxyRefreshIT
    extends AbstractNexusProxyP2IT
{
    public NXCM0670UpdateSiteProxyRefreshIT()
    {
        super( "nxcm0670" );
    }

    @Test
    public void test()
        throws Exception
    {
        final File nexusDir = new File( "target/nexus/nexus-work-dir/storage/nxcm0670" );
        final File remoteDir = new File( "target/nexus/proxy-repo/nxcm0670" );

        TaskScheduleUtil.run( "1" );
        TaskScheduleUtil.waitForAllTasksToStop();

        Assert.assertTrue( new File( nexusDir, "plugins/com.sonatype.nexus.p2.its.bundle_1.0.0.jar" ).exists() );

        FileUtils.copyFile( new File( remoteDir, "site-empty.xml" ), new File( remoteDir, "site.xml" ) );

        TaskScheduleUtil.run( "1" );
        TaskScheduleUtil.waitForAllTasksToStop();

        Assert.assertFalse( new File( nexusDir, "plugins/com.sonatype.nexus.p2.its.bundle_1.0.0.jar" ).exists() );

    }
}